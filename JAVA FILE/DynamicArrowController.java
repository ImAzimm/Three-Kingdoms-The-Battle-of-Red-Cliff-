/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DynamicArrowController {
    
    @FXML
    private Button start;
    @FXML
    private Label instruction;
    @FXML
    private Label instructionLifeSpan;
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
    private TextField lifeFront;
    @FXML
    private TextField lifeLeft;
    @FXML
    private TextField lifeRight;
    @FXML
    private TextField lifeBack;
    @FXML
    private TextArea answer;
    
    static Random rng = new Random();
    static Scanner sc = new Scanner(System.in);
    static int totalUsed = 0;
    
    DynamicBoat ship = new DynamicBoat();
    ArrayList<Integer> arrowShot = new ArrayList<>();
    ArrayList<String> direction = new ArrayList<>();
    ArrayList<Integer> arrowRecieved = new ArrayList<>();
    int numOfAttack, numArrow=0;
    StringBuilder sb = new StringBuilder();
    StringBuilder output = new StringBuilder();
    
    public void start(ActionEvent event)throws Exception{
        if (frontField.getText().isEmpty() || rightField.getText().isEmpty() || leftField.getText().isEmpty() || backField.getText().isEmpty()){
            answer.setText("Information is not complete");
            procedure.setText("Information is not complete");
            return;
        }
        if (lifeFront.getText().isEmpty() || lifeLeft.getText().isEmpty() || lifeRight.getText().isEmpty() || lifeBack.getText().isEmpty()){
            answer.setText("Information is not complete");
            procedure.setText("Information is not complete");
            return;
        }
        if (Integer.parseInt(frontField.getText())>100 || Integer.parseInt(rightField.getText())>100 || Integer.parseInt(leftField.getText())>100 || Integer.parseInt(backField.getText())>100){
            answer.setText("Strawmen for each side cannot more than 100");
            procedure.setText("Strawmen for each side cannot more than 100");
            return;
        }
        if (Integer.parseInt(lifeFront.getText())>10 || Integer.parseInt(lifeRight.getText())>10 || Integer.parseInt(lifeLeft.getText())>10 || Integer.parseInt(lifeBack.getText())>10){
            answer.setText("Lifespan for each side cannot more than 10");
            procedure.setText("Lifespan for each side cannot more than 10");
            return;
        }
        ship.setFront(getStrawman("front"),getLifespan("front"));
        ship.setLeft(getStrawman("left"),getLifespan("left"));
        ship.setRight(getStrawman("right"),getLifespan("right"));
        ship.setBack(getStrawman("back"),getLifespan("back"));
        
        numOfAttack = rng.nextInt(5,10);
        sb.append("Procedure:\n");
        
        for(int i=0 ; i<numOfAttack ; i++){

            //Dynamic arrow borrowing
            arrowShot.add(rng.nextInt(2000));

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
    
    public int getStrawman(String direction) throws Exception{
        int frontStrawmen = Integer.parseInt(frontField.getText());
        int leftStrawmen = Integer.parseInt(leftField.getText());
        int rightStrawmen = Integer.parseInt(rightField.getText());
        int backStrawmen = Integer.parseInt(backField.getText());
        int total = frontStrawmen + leftStrawmen + rightStrawmen + backStrawmen;
        
        if(total >300){
            answer.setText("Number of strawmen exceed 300!");
            procedure.setText("Number of strawmen exceed 300!");
            throw new Exception("Number of strawmen exceed 300!");
        }
        if(frontStrawmen<0 || leftStrawmen<0 || rightStrawmen<0 || backStrawmen<0){
            answer.setText("Enter positive value!");
            procedure.setText("Enter positive value!");
            throw new Exception("Enter positive value!");
        }
        if(direction.equals("right")){
            return rightStrawmen;
        }else if(direction.equals("left")){
            return leftStrawmen;
        }else if(direction.equals("front")){
            return frontStrawmen;
        }else{
            return backStrawmen;
        }
    }
    
    public int getLifespan(String direction) throws Exception{
        int frontLife = Integer.parseInt(lifeFront.getText());
        int leftLife = Integer.parseInt(lifeLeft.getText());
        int rightLife = Integer.parseInt(lifeRight.getText());
        int backLife = Integer.parseInt(lifeBack.getText());
        
        if(frontLife<0 || leftLife<0 || rightLife<0 || backLife<0){
            answer.setText("Enter positive value!");
            procedure.setText("Enter positive value!");
            throw new Exception("Enter positive value!");
        }
        if(frontLife>5 || leftLife>5 || rightLife>5 || backLife>5){
            answer.setText("Life span cannot more than 5.");
            procedure.setText("Life span cannot more than 5.");
            throw new Exception("Enter positive value!");
        }
        if(direction.equals("right")){
            return frontLife;
        }else if(direction.equals("left")){
            return leftLife;
        }else if(direction.equals("front")){
            return rightLife;
        }else{
            return backLife;
        }
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
}
