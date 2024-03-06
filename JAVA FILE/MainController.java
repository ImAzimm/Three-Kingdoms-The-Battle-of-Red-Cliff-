/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {   
    @FXML
    private Button arrange;
    @FXML
    private Button close;
    @FXML
    private Button game;
    
    public void arrange(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/Tree.fxml"));
        Scene scene = new Scene(root,740,565);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wu Kingdom Tree Table View");
        primaryStage.show();
    }
    
    public void game(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/GameMenu.fxml"));
        Scene scene = new Scene(root,760,550);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Menu");
        primaryStage.show();
    }
    
    public void close(ActionEvent event){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
}
