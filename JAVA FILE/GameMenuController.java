/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameMenuController {

    @FXML
    private Button arrow;
    @FXML
    private Button enemy;
    @FXML
    private Button food;
    @FXML
    private Button huaRong;
    @FXML
    private Button redCliff;
    @FXML
    private Button encrypted;
    @FXML
    private Button back;
    
    public void arrow(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/BorrowingArrows.fxml"));
        Scene scene = new Scene(root,765,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Borrowing Arrows with Straw Boatss");
        primaryStage.show();
    } 
    
    public void enemy(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/EnemyFortress.fxml"));
        Scene scene = new Scene(root,750,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enemy Fortress Attack Simulation");
        primaryStage.show();
    } 
    
    public void food(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/FoodHarvesting.fxml"));
        Scene scene = new Scene(root,750,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Food Harvesting");
        primaryStage.show();
    } 
    
    public void encrypted(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/EncryptedText.fxml"));
        Scene scene = new Scene(root,760,580);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encrypted Text");
        primaryStage.show();
    } 
    
    public void redCliff(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/RedCliff.fxml"));
        Scene scene = new Scene(root,750,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Red Cliff on Fire");
        primaryStage.show();
    }
    
    public void huaRong(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/HuaRongRoad.fxml"));
        Scene scene = new Scene(root,750,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Engaging Cao Cao at Hua Rong Road");
        primaryStage.show();
    }
    
    public void back(ActionEvent event)throws Exception{
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
}
