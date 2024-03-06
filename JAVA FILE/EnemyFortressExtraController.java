/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EnemyFortressExtraController implements Initializable {

    @FXML
    private ImageView map;
    @FXML
    private TextField baseCamp;
    @FXML
    private Button back;
    @FXML
    private Button search;
    @FXML
    public ComboBox<String> general;
    @FXML
    private TextArea outputArea;
    
    private Map<Integer, List<Edge>> adjList;
    ObservableList<String> list = FXCollections.observableArrayList("Sun Quan","Zhang Zhao","Zhou Yu","Xu Sheng","Zhu Ge Jin","Lu Su","Tai Shi Ci","Xiao Qiao","Da Qiao","Zhou Tai","Gan Ning","Lu Meng","Huang Gai");
    General xuSheng = new General("Xu Sheng", "Archer", 90, 78, 72, 40, 94);
    General sunQuan = new General("Sun Quan", "Cavarly", 96, 98, 72, 77, 95);
    General zhangZhao = new General("Zhang Zhao", "Archer", 22, 80, 89, 99, 60);
    General zhouYu = new General("Zhou Yu", "Cavarly", 80, 86, 97, 80, 90);
    General zhuGeJin = new General("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71);
    General luSu = new General("Lu Su", "Infantry", 43, 87, 84, 88, 53);
    General taiShiCi = new General("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97);
    General xiaoQiao = new General("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34);
    General daQiao = new General("Da Qiao", "Cavalry", 39, 62, 90, 62, 41);
    General zhouTai = new General("Zhou Tai", "Infantry", 92, 89, 72, 43, 99);
    General ganNing = new General("Gan Ning", "Archer", 98, 92, 45, 23, 97);
    General luMeng = new General("Lu Meng", "Cavalry", 70, 77, 93, 83, 88);
    General huangGai = new General("Huang Gai", "Infantry", 83, 98, 72, 42, 89);
    StringBuilder information = new StringBuilder();
    StringBuilder output = new StringBuilder();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        general.setItems(list);
        map.setImage(new Image("/img/mapExtra.png"));
        information.append("- Edges with flat road\n\t1 - 6\n\t1 - 3\n\t5 - 6\n\t7 - 8\n\t7 - 9\n\t1 - 10\n\t9 - 10\n\n");
        information.append("- Edges with forest\n\t1 - 2\n\t5 - 7\n\t6 - 7\n\t8 - 10\n\n");
        information.append("- Edges with swamp\n\t2 - 4\n\t3 - 4\n\t4 - 5\n\t8 - 9\n\n");
        information.append("- Edges with plank road\n\t3 - 7\n\t6 - 8\n\n");
        information.append("The general’s speed when entering specific edges\n");
        information.append("-Cavalry - speed : 2 km/h\n\tFlat road - x 3\n\tForest - x 0.8\n\tSwamp - x 0.3\n\tPlank road - x 0.5\n\n");
        information.append("-Archer - speed: 1 km/h\n\tFlat road - x 2\n\tForest - x 1\n\tSwamp - x 2.5\n\tPlank road - x 0.5\n\n");
        information.append("- Infantry - speed:1 km/h\n\tFlat road - x 2\n\tForest - x 2.5\n\tSwamp - x 1\n\tPlank road - x 0.5\n\n");
        outputArea.setText(information.toString());
    }    
    
    public EnemyFortressExtraController(){
        adjList = new HashMap<>();
        createGraph();
    }
    
    public void search(ActionEvent event){
        EnemyFortressExtraController graph = new EnemyFortressExtraController();
        int start = 1;
        
        if(baseCamp.getText().isEmpty()){
            outputArea.setText("Please enter a valid integer for base camp");
            return;
        }
        int value = Integer.parseInt(baseCamp.getText());
        int end = value;
        if (value >10 || value<=0) {
            outputArea.setText("Please enter a valid integer for base camp");
            return;
        }
        
        if(general.getValue()==null){
            outputArea.setText("Please select general");
            return;
        }
        String name = general.getValue();
        
        List<Integer> shortestPath = graph.findShortestTimePath(start, end, getArmyType(name));
        double totalDistance = calculateTotalDistance(shortestPath);
        output.append(information);
        output.append("Shortest time path from " + start + " to " + end + " for " + getArmyType(name) + " :");
        String pathStr = shortestPath.get(0).toString();
        for (int i = 1; i < shortestPath.size(); i++) {
            int node = shortestPath.get(i);
            pathStr += " -> " + node;
        }
        output.append(pathStr);
        output.append("\nTotal distance: " + totalDistance + " km");
        double totalTime = graph.findShortestTotalTime(start, end, getArmyType(name));
        output.append("\nTotal time: " + totalTime + " hours");
        outputArea.setText(output.toString());
        output = new StringBuilder();
    }
    
    private static class Edge {
        public int destination;
        public int distance;
        public String edgeType;

        public Edge(int destination, int distance, String edgeType) {
            this.destination = destination;
            this.distance = distance;
            this.edgeType = edgeType;
        }
    }
    
    private void createGraph() {
        addEdge(1, 3, 18, "flat road");
        addEdge(3, 1, 18, "flat road");
        addEdge(1, 2, 10, "forest");
        addEdge(2, 1, 10, "forest");
        addEdge(1, 6, 20, "flat road");
        addEdge(6, 1, 20, "flat road");
        addEdge(1, 10, 16, "flat road");
        addEdge(10, 1, 16, "flat road");
        addEdge(3, 4, 12, "swamp");
        addEdge(4, 3, 12, "swamp");
        addEdge(2, 4, 10, "swamp");
        addEdge(4, 2, 10, "swamp");
        addEdge(3, 7, 28, "plank road");
        addEdge(7, 3, 28, "plank road");
        addEdge(4, 5, 12, "swamp");
        addEdge(5, 4, 12, "swamp");
        addEdge(5, 6, 17, "flat road");
        addEdge(6, 5, 17, "flat road");
        addEdge(5, 7, 10, "forest");
        addEdge(7, 5, 10, "forest");
        addEdge(6, 7, 23, "forest");
        addEdge(7, 6, 23, "forest");
        addEdge(6, 8, 35, "plank road");
        addEdge(8, 6, 35, "plank road");
        addEdge(7, 8, 19, "flat road");
        addEdge(8, 7, 19, "flat road");
        addEdge(8, 10, 12, "forest");
        addEdge(10, 8, 12, "forest");
        addEdge(9, 10, 18, "flat road");
        addEdge(10, 9, 18, "flat road");
        addEdge(7, 9, 17, "flat road");
        addEdge(9, 7, 17, "flat road");
        addEdge(8, 9, 7, "swamp");
        addEdge(9, 8, 7, "swamp");
    }
    
    private void addEdge(int source, int destination, int distance, String edgeType) {
        if (!adjList.containsKey(source))
            adjList.put(source, new ArrayList<>());
        adjList.get(source).add(new Edge(destination, distance, edgeType));
    }
    
    public List<Integer> findShortestTimePath(int start, int end, String armyType) {
        PriorityQueue<GraphNode> pq = new PriorityQueue<>();
        pq.add(new GraphNode(start, 0.0));

        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> previousNodes = new HashMap<>();
        for (int node : adjList.keySet()) {
            distances.put(node, Double.POSITIVE_INFINITY);
            previousNodes.put(node, null);
        }
        distances.put(start, 0.0);

        while (!pq.isEmpty()) {
            GraphNode currentNode = pq.poll();
            int node = currentNode.node;
            double currentDistance = currentNode.distance;

            if (node == end)
                break;

            if (currentDistance > distances.get(node))
                continue;

            List<Edge> neighbors = adjList.get(node);
            if (neighbors != null) {
                for (Edge neighbor : neighbors) {
                    double neighborDistance = calculateTravelTime(neighbor, armyType);
                    double newDistance = currentDistance + neighborDistance;
                    output.append(newDistance);
                    if (newDistance < distances.get(neighbor.destination)) {
                        distances.put(neighbor.destination, newDistance);
                        previousNodes.put(neighbor.destination, node);
                        pq.add(new GraphNode(neighbor.destination, newDistance));
                    }
                }
            }
            
        }
        

        List<Integer> shortestPath = new ArrayList<>();
        Integer currentNode = end;
        while (currentNode != null) {
            shortestPath.add(0, currentNode);
            currentNode = previousNodes.get(currentNode);
        }

        return shortestPath;
    }
    
    private double calculateTravelTime(Edge edge, String armyType) {
        double speed;

        if (armyType.equals("Cavalry")) {
            speed = 2.0;
            if (edge.edgeType.equals("flat road"))
                speed *= 3;
            else if (edge.edgeType.equals("forest"))
                speed *= 0.8;
            else if (edge.edgeType.equals("swamp"))
                speed *= 0.3;
            else if (edge.edgeType.equals("plank road"))
                speed *= 0.5;
        } else if (armyType.equals("Archer")) {
            speed = 1.0;
            if (edge.edgeType.equals("flat road"))
                speed *= 2;
            else if (edge.edgeType.equals("forest"))
                speed *= 1;
            else if (edge.edgeType.equals("swamp"))
                speed *= 2.5;
            else if (edge.edgeType.equals("plank road"))
                speed *= 0.5;
        } else {
            // Infantry
            speed = 1.0;
            if (edge.edgeType.equals("flat road"))
                speed *= 2;
            else if (edge.edgeType.equals("forest"))
                speed *= 2.5;
            else if (edge.edgeType.equals("swamp"))
                speed *= 1;
            else if (edge.edgeType.equals("plank road"))
                speed *= 0.5;
        }

        return (double) edge.distance / speed;
    }
    
    private static class GraphNode implements Comparable<GraphNode> {
        public int node;
        public double distance;

        public GraphNode(int node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(GraphNode other) {
            return Double.compare(distance, other.distance);
        }
    }
    
    public String getArmyType(String general){
        if(general.equals("Xu Sheng")){
            return xuSheng.getArmyType();
        }else if(general.equals("Zhu Ge Jin")){
            return zhuGeJin.getArmyType();
        }else if(general.equals("Sun Quan")){
            return sunQuan.getArmyType();
        }else if(general.equals("Zhang Zhao")){
            return zhangZhao.getArmyType();
        }else if(general.equals("Zhou Yu")){
            return zhouYu.getArmyType();
        }else if(general.equals("Lu Su")){
            return luSu.getArmyType();
        }else if(general.equals("Tai Shi Ci")){
            return taiShiCi.getArmyType();
        }else if(general.equals("Xiao Qiao")){
            return xiaoQiao.getArmyType();
        }else if(general.equals("Da Qiao")){
            return daQiao.getArmyType();
        }else if(general.equals("Zhou Tai")){
            return zhouTai.getArmyType();
        }else if(general.equals("Gan Ning")){
            return ganNing.getArmyType();
        }else if(general.equals("Lu Meng")){
            return luMeng.getArmyType();
        }else{
            return huangGai.getArmyType();
        }
    }
    
    private double calculateTotalDistance(List<Integer> shortestPath) {
        double totalDistance = 0.0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int source = shortestPath.get(i);
            int destination = shortestPath.get(i + 1);
            List<Edge> edges = adjList.get(source);
            if (edges != null) {
                for (Edge edge : edges) {
                    if (edge.destination == destination) {
                        totalDistance += edge.distance;
                        break;
                    }
                }
            }
        }
        return totalDistance;
    }
    
    public double findShortestTotalTime(int start, int end, String armyType) {
        PriorityQueue<GraphNode> pq = new PriorityQueue<>();
        pq.add(new GraphNode(start, 0.0));

        Map<Integer, Double> times = new HashMap<>();
        Map<Integer, Integer> previousNodes = new HashMap<>();
        for (int node : adjList.keySet()) {
            times.put(node, Double.POSITIVE_INFINITY);
            previousNodes.put(node, null);
        }
        times.put(start, 0.0);

        while (!pq.isEmpty()) {
            GraphNode currentNode = pq.poll();
            int node = currentNode.node;
            double currentTime = currentNode.distance;

            if (node == end)
                break;

            if (currentTime > times.get(node))
                continue;

            List<Edge> neighbors = adjList.get(node);
            if (neighbors != null) {
                for (Edge neighbor : neighbors) {
                    double neighborTime = calculateTravelTime(neighbor, armyType);
                    double newTime = currentTime + neighborTime;

                    if (newTime < times.get(neighbor.destination)) {
                        times.put(neighbor.destination, newTime);
                        previousNodes.put(neighbor.destination, node);
                        pq.add(new GraphNode(neighbor.destination, newTime));
                    }
                }
            }
        }

        double totalTime = times.get(end);
        return totalTime;
    }
    
    public void back(ActionEvent event){
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
}
