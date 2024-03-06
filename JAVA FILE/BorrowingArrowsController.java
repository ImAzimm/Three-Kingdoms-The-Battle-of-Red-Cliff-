/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;


import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BorrowingArrowsController {
    
    static Random rng = new Random();
    @FXML
    private Button start;
    @FXML
    private Button dynamicArrow;
    @FXML
    private Label instruction;
    @FXML
    private Button close;
    @FXML
    private TextArea procedure;
    @FXML
    private TextField frontField;
    @FXML
    private TextField leftField;
    @FXML
    private TextField rightField;
    @FXML
    private TextField backField;
    @FXML
    private TextArea answer;
    
    Boat ship = new Boat();
    ArrayList<Integer> arrowShot = new ArrayList<>();
    ArrayList<String> direction = new ArrayList<>();
    ArrayList<Integer> arrowRecieved = new ArrayList<>();
    int numOfAttack, numArrow=0;
    StringBuilder sb = new StringBuilder();
    StringBuilder output = new StringBuilder();
    
    public void start(ActionEvent event){
        if (frontField.getText().isEmpty() || rightField.getText().isEmpty() || leftField.getText().isEmpty() || backField.getText().isEmpty()){
            answer.setText("Information is not complete");
            procedure.setText("Information is not complete");
            return;
        }
        if (Integer.parseInt(frontField.getText())>100 || Integer.parseInt(rightField.getText())>100 || Integer.parseInt(leftField.getText())>100 || Integer.parseInt(backField.getText())>100){
            answer.setText("Strawmen for each side cannot more than 100");
            procedure.setText("Strawmen for each side cannot more than 100");
            return;
        }
        ship.setFront(Integer.parseInt(frontField.getText()));
        ship.setLeft(Integer.parseInt(leftField.getText()));
        ship.setRight(Integer.parseInt(rightField.getText()));
        ship.setBack(Integer.parseInt(backField.getText()));
        //randomize the number of attack from enemy
        numOfAttack = rng.nextInt(5,10);
        
        sb.append("Procedure:\n");
        for(int i=0 ; i<numOfAttack ; i++){
            //randomize number of arrow from enemy. The number of arrow will decrease by 20% - 40%
            if(i == 0)
                arrowShot.add(2000);
            else
                arrowShot.add(arrowShot.get(i-1) * rng.nextInt(60, 80) / 100);

            //randomize direction of ship using the method created
            direction.add(randomDirection());
            
            sb.append("\n" + (i+1) + "st wave arrow\n");
            sb.append(ship.procedure(direction.get(i), arrowShot.get(i)));
            arrowRecieved.add(ship.Serang(direction.get(i), arrowShot.get(i)));
            numArrow += arrowRecieved.get(i);
        }
        
        printArray("Arrow", arrowShot);
        output.append("\nOutput: \n");
        printArray("Ship direction", direction);
        printArray("Arrow recieved", arrowRecieved);
        output.append("Total: " + numArrow);
        procedure.setText(sb.toString());
        answer.setText(output.toString());
        
    }
    
    public void printArray(String promt, ArrayList<?> list){
        output.append(promt +": [");
        for(Object item : list){
            output.append(item +" ");
        }
        output.append("\b\b]\n");
    }
    
    public static String randomDirection(){
        int dice = rng.nextInt(4);
        if(dice == 0)
            return "left";
        else if(dice == 1)
            return "right";
        else if(dice == 2)
            return "front";
        else
            return "back";
    }
    
    public void close(ActionEvent event){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    
    public void DynamicArrow(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/DynamicArrow.fxml"));
        Scene scene = new Scene(root,760,580);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dynamic Arrow Borrowing");
        primaryStage.show();
    }
    
    
    
}
