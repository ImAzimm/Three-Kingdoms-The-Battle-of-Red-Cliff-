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

public class FoodHarvestingController implements Initializable {
    
    @FXML
    private Button start;
    @FXML
    private Button next;
    @FXML
    private Button close;
    @FXML
    private ImageView map;
    @FXML
    private TextField nodeField;
    @FXML
    private TextArea pathArea;
    @FXML
    private Label instruction;
    
    private static Map<Integer, List<Integer>> adjList;
    static List<Integer> throwNodes = new ArrayList<>();
    StringBuilder output = new StringBuilder();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        map.setImage(new Image("/img/map.png"));
    }

    public void start(ActionEvent event){
        output = new StringBuilder();
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
        
        if(nodeField.getText().isEmpty()){
            output.append("Please fill in node without food");
            return;
        }
        String nodesInput = nodeField.getText();
        if (nodesInput.isEmpty()) {
            pathArea.setText("Please enter node values.");
            return;
        }
        String[] nodes = nodesInput.split(" ");
        for (String node : nodes) {
            throwNodes.add(Integer.parseInt(node));
        }
        
        int startNode = 1;
        List<Integer> path = findPath(startNode, throwNodes);
        
        output.append("\nNode without food: " + nodesInput + "\n");
        output.append("Path:\n");
        if (path.isEmpty()) {
            output.append("No valid path exists.\n");
        } else {
            for (int node : path) {
                output.append(node + " -> ");
            }
            output.append(startNode + "\n");
        }
        pathArea.setText(output.toString());
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
            output.append("Node cannot be 1.");
            return false;
        }
        for(int nodes : nodesWithoutFood){
            if(nodes>10 || nodes<0){
                output.append("Please enter correct node");
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
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    
    public void next(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/FoodHarvesting1.fxml"));
        Scene scene = new Scene(root,760,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Food Harvesting 1");
        primaryStage.show();
    }
    
}
