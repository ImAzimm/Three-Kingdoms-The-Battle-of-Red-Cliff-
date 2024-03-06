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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EncryptedTextController {
    
    @FXML
    private Button MoreSecure;
    @FXML
    private Button encrypt;
    @FXML
    private Button decrypt;
    @FXML
    private Button back;
    @FXML
    private TextArea decryptField;
    @FXML
    private TextArea encryptField;
    @FXML
    private TextField shiftFieldEncrypt;
    @FXML
    private TextField shiftFieldDecrypt;
    @FXML
    private TextArea answer;
    
    Encryption encryptor = new Encryption();
    String input;
    int shift;
    
    public void moreSecure(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/MoreSecuredEncryption.fxml"));
        Scene scene = new Scene(root,750,565);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("More Secured Encryption");
        primaryStage.show();
    }
    public void encrypt(ActionEvent event)throws Exception{
        input = encryptField.getText();
        if (shiftFieldEncrypt.getText().isEmpty()){
            answer.setText("Please enter a shift value.");
            return;
        }
        shift = Integer.parseInt(shiftFieldEncrypt.getText());
        StringBuilder sb = new StringBuilder();
        sb.append("Message encrypt to: \n");
        sb.append(encryptor.encrypt(input, shift, '$'));
        answer.setText(sb.toString());
    }
    public void decrypt(ActionEvent event)throws Exception{
        input = decryptField.getText();
        if (shiftFieldDecrypt.getText().isEmpty()){
            answer.setText("Please enter a shift value.");
            return;
        }
        shift = Integer.parseInt(shiftFieldDecrypt.getText());
        StringBuilder sb = new StringBuilder();
        sb.append("Message decrypt to: \n");
        sb.append(encryptor.decrypt(input, shift, '$'));
        answer.setText(sb.toString());
    }
    public void back(ActionEvent event){
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
}
