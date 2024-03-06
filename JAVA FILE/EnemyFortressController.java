/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EnemyFortressController implements Initializable {
    
    @FXML
    private Button bestPath;
    @FXML
    private Button next;
    @FXML
    private Button possiblePath;
    @FXML
    private ImageView map;
    @FXML
    private TextField baseCamp;
    @FXML
    private Label showPath;
    @FXML
    private TextArea outputArea;
    
    private Map<Integer, List<Integer>> adjList;
    StringBuilder sb = new StringBuilder();
    
    public EnemyFortressController(){
        adjList = new HashMap<>();
        // Adding directed edges
        adjList.put(1, Arrays.asList(2, 3, 10, 6));
        adjList.put(2, Arrays.asList(1,4));
        adjList.put(3, Arrays.asList(1,7,4));
        adjList.put(4, Arrays.asList(2,3,5));
        adjList.put(5, Arrays.asList(4,6,7));
        adjList.put(6, Arrays.asList(1,5,7,8));
        adjList.put(7, Arrays.asList(5,6,8,9));
        adjList.put(8, Arrays.asList(6,7,9,10));
        adjList.put(9, Arrays.asList(7,8,10));
        adjList.put(10, Arrays.asList(1,8,9));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        map.setImage(new Image("/img/map.png"));
    }    
    
    public void possiblePath(ActionEvent event)throws Exception{
        EnemyFortressController graph = new EnemyFortressController();
        int start = 1;
        
        if(baseCamp.getText().isEmpty()){
            outputArea.setText("Please enter a valid integer for base camp");
            return;
        }
        int value = Integer.parseInt(baseCamp.getText());
        setImage(value);
        int end = value;
        if (value >10 || value<=0) {
            outputArea.setText("Please enter a valid integer for base camp");
            return;
        }
        
        if (value >10 || value<=0 || baseCamp.getText().isEmpty()) {
            showPath.setText("Please enter a valid integer for base camp");
            sb.append("Please enter a valid integer for base camp");
            return;
        }
        
        List<List<Integer>> paths = graph.findAllPaths(start, end);
        sb.append("All possible paths from " + start + " to " + end + ": \n");
        
        for (List<Integer> path : paths) {
            String pathStr = path.get(0).toString();
            for (int i = 1; i < path.size(); i++) {
                pathStr += " -> " + path.get(i);
            }
            sb.append(pathStr + "\n");
        }
        outputArea.setText(sb.toString());
        sb = new StringBuilder();
    }
    
    public void bestPath(ActionEvent event)throws Exception{
        if(baseCamp.getText().isEmpty()){
            outputArea.setText("Please enter a valid integer for base camp");
            return;
        }
        int value = Integer.parseInt(baseCamp.getText());
        if (value >10 || value<=0) {
            outputArea.setText("Please enter a valid integer for base camp");
            return;
        }
        setImage(value);
        EnemyFortressController graph = new EnemyFortressController();
        int start = 1;
        
        int end = value;
        
        List<List<Integer>> paths = graph.findAllPaths(start, end);
        // sort the paths in increasing order of length
        paths.sort(Comparator.comparingInt(List::size));
        StringBuilder sb = new StringBuilder();
        sb.append("Best path: \n");
        
        for (int i = 0; i < Math.min(2, paths.size()); i++) {
            List<Integer> path = paths.get(i);

            String pathString = path.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("-> "));
            
            sb.append(pathString + "\n");
        }
        outputArea.setText(sb.toString());
    }
    
    public void setImage(int campNum){
        switch (campNum) {
            case 1:
                map.setImage(new Image("/img/map1.png"));
                break;
            case 2:
                map.setImage(new Image("/img/map2.png"));
                break;
            case 3:
                map.setImage(new Image("/img/map3.png"));
                break;
            case 4:
                map.setImage(new Image("/img/map4.png"));
                break;
            case 5:
                map.setImage(new Image("/img/map5.png"));
                break;
            case 6:
                map.setImage(new Image("/img/map6.png"));
                break;
            case 7:
                map.setImage(new Image("/img/map7.png"));
                break;
            case 8:
                map.setImage(new Image("/img/map8.png"));
                break;
            case 9:
                map.setImage(new Image("/img/map9.png"));
                break;
            case 10:
                map.setImage(new Image("/img/map10.png"));
                break;
            default:
                break;
        }
    }
    
    public List<List<Integer>> findAllPaths(int start, int end) {
        List<List<Integer>> paths = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.offer(initialPath);

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int lastNode = currentPath.get(currentPath.size() - 1);

            if (lastNode == end) {
                paths.add(currentPath);
            }

            for (int neighbor : adjList.getOrDefault(lastNode, new ArrayList<>())) {
                if (!currentPath.contains(neighbor)) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.offer(newPath);
                }
            }
        }

        return paths;
    }
    
    public void next(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/EnemyFortressExtra.fxml"));
        Scene scene = new Scene(root,840,630);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enemy Fortress Attack Simulation I");
        primaryStage.show();
    }
    
}
