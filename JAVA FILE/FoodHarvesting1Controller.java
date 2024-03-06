/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FoodHarvesting1Controller implements Initializable {
    @FXML
    public ComboBox<String> general1;
    @FXML
    public ComboBox<String> general2;
    @FXML
    public ComboBox<String> general3;
    @FXML
    private Button search;
    @FXML
    private Button back;
    @FXML
    private TextArea foodProduction;
    @FXML
    private Label instruction;
    @FXML
    private ImageView general1pic;
    @FXML
    private ImageView general2pic;
    @FXML
    private ImageView general3pic;
    @FXML
    private TextField nodeField;
    
    ObservableList<String> list = FXCollections.observableArrayList("Sun Quan","Zhang Zhao","Zhou Yu","Xu Sheng","Zhu Ge Jin","Lu Su","Tai Shi Ci","Xiao Qiao","Da Qiao","Zhou Tai","Gan Ning","Lu Meng","Huang Gai");
    StringBuilder output = new StringBuilder();
    StringBuilder errorMessage = new StringBuilder();
    
    private static Map<Integer, List<Integer>> adjList;
    static List<Integer> throwNodes = new ArrayList<>();
    int totalIntelligence =0;
    int totalPolitic =0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        general1.setItems(list);
        general2.setItems(list);
        general3.setItems(list);
    }    
    
    General sunQuan = new General("Sun Quan", "Cavarly", 96, 98, 72, 77, 95);
    General zhangZhao = new General("Zhang Zhao", "Archer", 22, 80, 89, 99, 60);
    General zhouYu = new General("Zhou Yu", "Cavarly", 80, 86, 97, 80, 90);
    General xuSheng = new General("Xu Sheng", "Archer", 90, 78, 72, 40, 94);
    General zhuGeJin = new General("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71);
    General luSu = new General("Lu Su", "Infantry", 43, 87, 84, 88, 53);
    General taiShiCi = new General("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97);
    General xiaoQiao = new General("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34);
    General daQiao = new General("Da Qiao", "Cavalry", 39, 62, 90, 62, 41);
    General zhouTai = new General("Zhou Tai", "Infantry", 92, 89, 72, 43, 99);
    General ganNing = new General("Gan Ning", "Archer", 98, 92, 45, 23, 97);
    General luMeng = new General("Lu Meng", "Cavalry", 70, 77, 93, 83, 88);
    General huangGai = new General("Huang Gai", "Infantry", 83, 98, 72, 42, 89);
    
    public void start(ActionEvent event){
        StringBuilder output = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        if(general1.getValue()!= general2.getValue() && general1.getValue()!= general3.getValue() && general2.getValue()!= general3.getValue()){
            setImage(general1pic ,general1.getValue());
            setImage(general2pic ,general2.getValue());
            setImage(general3pic ,general3.getValue());
            
            output.append("Generals' Politic point: \n");
            output.append(general1.getValue() + " ：" + pointCalculate(general1.getValue(), "Politic") + "\n");
            output.append(general2.getValue() + " ：" + pointCalculate(general2.getValue(), "Politic") + "\n");
            output.append(general3.getValue() + " ：" + pointCalculate(general3.getValue(), "Politic") + "\n");
            totalPolitic = pointCalculate(general1.getValue(), "Politic") + pointCalculate(general2.getValue(), "Politic") + pointCalculate(general3.getValue(), "Politic");
            output.append("Total Politic point: " + totalPolitic + "\n");
            output.append(levelSelect(totalPolitic) + " level");
            
            output.append("\n\nGenerals' Intelligence point: \n");
            output.append(general1.getValue() + " ：" + pointCalculate(general1.getValue(), "Intelligence") + "\n");
            output.append(general2.getValue() + " ：" + pointCalculate(general2.getValue(), "Intelligence") + "\n");
            output.append(general3.getValue() + " ：" + pointCalculate(general3.getValue(), "Intelligence") + "\n");
            totalIntelligence = pointCalculate(general1.getValue(), "Intelligence") + pointCalculate(general2.getValue(), "Intelligence") + pointCalculate(general3.getValue(), "Intelligence");
            output.append("Total Intelligence point: " + totalIntelligence + "\n");
            output.append(levelSelect(totalIntelligence) + " level");
        }else{
            general1pic.setImage(null);
            general2pic.setImage(null);
            general3pic.setImage(null);
            foodProduction.setText("Please choose 3 different generals" + "\n");
            return;
        }
        throwNodes = new ArrayList<>();
        adjList = new HashMap<>();
        adjList.put(1, Arrays.asList(2, 3, 10, 6));
        adjList.put(2, Arrays.asList(1, 4));
        adjList.put(3, Arrays.asList(1, 7, 4));
        adjList.put(4, Arrays.asList(2, 3, 5));
        adjList.put(5, Arrays.asList(4, 6, 7));
        adjList.put(6, Arrays.asList(1, 5, 7, 8));
        adjList.put(7, Arrays.asList(5, 6, 8, 9));
        adjList.put(8, Arrays.asList(6, 7, 9, 10));
        adjList.put(9, Arrays.asList(7, 8, 10));
        adjList.put(10, Arrays.asList(1, 8, 9));
        
        String nodesInput = nodeField.getText();
        if (nodesInput.isEmpty()) {
            foodProduction.setText("Please enter node values.");
            return;
        }
        String[] nodes = nodesInput.split(" ");
        for (String node : nodes) {
            throwNodes.add(Integer.parseInt(node));
        }
        
        int startNode = 1;
        List<Integer> path = findPath(startNode, throwNodes);
        
        output.append("\n\nNode without food: " + nodesInput + "\n");
        output.append("Path:\n");
        if (path.isEmpty()) {
            output.append("No valid path exists.\n");
        } else {
            for (int node : path) {
                output.append(node + " -> ");
            }
            output.append(startNode + "\n");
        }
        int visited = 9-nodes.length;
        if(path.isEmpty()){
            visited=0;
        }
        output.append("\nTotal Food Production (Politic) : " + foodCalculate(visited, levelSelect(totalPolitic), "Politic", totalPolitic));
        output.append("\nTotal Food Production (Intelligence) : " + foodCalculate(visited, levelSelect(totalIntelligence), "Intelligence", totalIntelligence)); 
        foodProduction.setText(errorMessage.toString());
        if(errorMessage.isEmpty()){
            foodProduction.setText(output.toString());
        }
        output = new StringBuilder();
        errorMessage = new StringBuilder();
    }
    
    public void setImage(ImageView generalPic, String generalName){
        if(generalName.equals("Xu Sheng")){
            generalPic.setImage(new Image("/img/xuSheng.png"));
        }else if(generalName.equals("Zhu Ge Jin")){
            generalPic.setImage(new Image("/img/zhuGeJin.png"));
        }else if(generalName.equals("Zhou Yu")){
            generalPic.setImage(new Image("/img/zhouYu.png"));
        }else if(generalName.equals("Zhang Zhao")){
            generalPic.setImage(new Image("/img/zhangZhao.png"));
        }else if(generalName.equals("Sun Quan")){
            generalPic.setImage(new Image("/img/sunQuan.png"));
        }else if(generalName.equals("Lu Su")){
            generalPic.setImage(new Image("/img/luSu.png"));
        }else if(generalName.equals("Tai Shi Ci")){
            generalPic.setImage(new Image("/img/taiShiCi.png"));
        }else if(generalName.equals("Xiao Qiao")){
            generalPic.setImage(new Image("/img/xiaoQiao.png"));
        }else if(generalName.equals("Da Qiao")){
            generalPic.setImage(new Image("/img/daQiao.png"));
        }else if(generalName.equals("Zhou Tai")){
            generalPic.setImage(new Image("/img/zhouTai.png"));
        }else if(generalName.equals("Gan Ning")){
            generalPic.setImage(new Image("/img/ganNing.png"));
        }else if(generalName.equals("Lu Meng")){
            generalPic.setImage(new Image("/img/luMeng.png"));
        }else if(generalName.equals("Huang Gai")){
            generalPic.setImage(new Image("/img/huangGai.png"));
        }
    }
    
    public Integer pointCalculate(String general, String ability){
        if(general.equals("Xu Sheng")){
            if(ability.equals("Politic"))
                return xuSheng.getPolitic();
            else
                return xuSheng.getIntelligence();
        }else if(general.equals("Zhou Yu")){
            if(ability.equals("Politic"))
                return zhouYu.getPolitic();
            else
                return zhouYu.getIntelligence();
        }else if(general.equals("Sun Quan")){
            if(ability.equals("Politic"))
                return sunQuan.getPolitic();
            else
                return sunQuan.getIntelligence();
        }else if(general.equals("Zhang Zhao")){
            if(ability.equals("Politic"))
                return zhangZhao.getPolitic();
            else
                return zhangZhao.getIntelligence();
        }else if(general.equals("Zhu Ge Jin")){
            if(ability.equals("Politic"))
                return zhuGeJin.getPolitic();
            else
                return zhuGeJin.getIntelligence();
        }else if(general.equals("Lu Su")){
            if(ability.equals("Politic"))
                return luSu.getPolitic();
            else
                return luSu.getIntelligence();
        }else if(general.equals("Tai Shi Ci")){
            if(ability.equals("Politic"))
                return taiShiCi.getPolitic();
            else
                return taiShiCi.getIntelligence();
        }else if(general.equals("Xiao Qiao")){
            if(ability.equals("Politic"))
                return xiaoQiao.getPolitic();
            else
                return xiaoQiao.getIntelligence();
        }else if(general.equals("Da Qiao")){
            if(ability.equals("Politic"))
                return daQiao.getPolitic();
            else
                return daQiao.getIntelligence();
        }else if(general.equals("Zhou Tai")){
            if(ability.equals("Politic"))
                return zhouTai.getPolitic();
            else
                return zhouTai.getIntelligence();
        }else if(general.equals("Gan Ning")){
            if(ability.equals("Politic"))
                return ganNing.getPolitic();
            else
                return ganNing.getIntelligence();
        }else if(general.equals("Lu Meng")){
            if(ability.equals("Politic"))
                return luMeng.getPolitic();
            else
                return luMeng.getIntelligence();
        }else{
            if(ability.equals("Politic"))
                return huangGai.getPolitic();
            else
                return huangGai.getIntelligence();
        }
        
    }
    
    public String levelSelect(int total){
        if(total>=250){
            return "S";
        }else if(total >=220){
            return "A";
        }else if(total >= 190){
            return "B";
        }else{
            return "C";
        }
    }
    
    public double foodCalculate(int nodeNum, String level, String ability, int total){
        double totalFood =0 ;
        if(ability.equals("Politic")){
            if(level.equals("S")){
                 totalFood = total*2*nodeNum;
                 return totalFood;
            }else if(level.equals("A")){
                totalFood = total*1.5*nodeNum;
                return totalFood;
            }else if(level.equals("B")){
                totalFood = total*1.2*nodeNum;
                return totalFood;
            }else{
                totalFood = total*1*nodeNum;
                return totalFood;
            }
        }else{
            if(level.equals("S")){
                totalFood = total*1.8*nodeNum;
                return totalFood;
            }else if(level.equals("A")){
                totalFood = total*1.3*nodeNum;
                return totalFood;
            }else if(level.equals("B")){
                totalFood = total*1*nodeNum;
                return totalFood;
            }else{
                totalFood = total*0.8*nodeNum;
                return totalFood;
            }
        }
        
    }
    
    public List<Integer> findPath(int startNode, List<Integer> nodesWithoutFood) {
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        
        dfs(startNode, nodesWithoutFood, path, visited);

        // If the path only contains the start node, return an empty path
        if (path.size() == 1) {
            return new ArrayList<>();
        }

        return path;
    }
    
    public boolean dfs(int currentNode, List<Integer> nodesWithoutFood, List<Integer> path, Set<Integer> visited) {
        // Base case: If the current node is one of the nodes without food, return false
        int size = throwNodes.size();
        if (nodesWithoutFood.contains(currentNode)) {
            errorMessage.append("Node cannot be 1.");
            return false;
        }
        for(int nodes : nodesWithoutFood){
            if(nodes>10 || nodes<0){
                errorMessage.append("Please enter correct node");
                return false;
            }
            if(nodes == 0){
                size = 0;
            }
        }
        visited.add(currentNode);
        path.add(currentNode);

        // Recursively visit adjacent nodes
        for (int neighbor : adjList.get(currentNode)) {
            if (visited.size() == adjList.size()-size && neighbor == 1) {
                return true;
            }
            if (!visited.contains(neighbor) && !nodesWithoutFood.contains(neighbor)) {
                if (dfs(neighbor, nodesWithoutFood, path, visited)) {
                    return true;
                }
            }
        }

        // If no path is found, remove the current node from the path
        path.remove(path.size() - 1);
        visited.remove(currentNode);

        return false;
    }
    
    public void close(ActionEvent event){
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
    
}
