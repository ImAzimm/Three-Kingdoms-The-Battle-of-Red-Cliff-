/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BinarySearchController implements Initializable {

    @FXML
    public ComboBox<String> general;
    @FXML
    private Button search;
    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Label strengthDisplay;
    @FXML
    private Label leadershipDisplay;
    @FXML
    private Label intelligenceDisplay;
    @FXML
    private Label politicDisplay;
    @FXML
    private Label hitPointDisplay;
    @FXML
    private ImageView generalPhoto;
    
    ObservableList<String> list = FXCollections.observableArrayList("Sun Quan","Zhang Zhao", "Zhou Yu","Xu Sheng","Zhu Ge Jin","Lu Su","Tai Shi Ci","Xiao Qiao","Da Qiao","Zhou Tai","Gan Ning","Lu Meng","Huang Gai");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        general.setItems(list);
    }
    Integer[] strength = {96,22,80,90,63,43,96,42,39,92,98,70,83};
    Integer[] leadership = {98,80,86,78,61,87,81,52,62,89,92,77,98};
    Integer[] intelligence = {72,89,97,72,88,84,43,89,90,72,45,93,72};
    Integer[] politic = {77,99,80,40,82,88,33,77,62,43,23,83,42};
    Integer[] hitPoint = {95,60,90,89,88,97,99,41,34,97,53,71,94};
    
    General sunQuan = new General("Sun Quan", "Cavalry", 96, 98, 72, 77, 95);
    General zhangZhao = new General("Zhang Zhao", "Archer", 22, 80, 89, 99, 60);
    General zhouYu = new General("Zhou Yu", "Cavalry", 80, 86, 97, 80, 90);
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
    
    public void search(ActionEvent event){
        if(general.getValue()==null){
            strengthDisplay.setText("Please select general");
            return;
        }
        if(general.getValue().equals("Xu Sheng")){
            generalPhoto.setImage(new Image("/img/xuSheng.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, xuSheng.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, xuSheng.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, xuSheng.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, xuSheng.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, xuSheng.getHitPoint()) + "th");
        }else if(general.getValue().equals("Zhu Ge Jin")){
            generalPhoto.setImage(new Image("/img/zhuGeJin.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, zhuGeJin.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, zhuGeJin.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, zhuGeJin.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, zhuGeJin.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, zhuGeJin.getHitPoint()) + "th");
        }else if(general.getValue().equals("Sun Quan")){
            generalPhoto.setImage(new Image("/img/sunQuan.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, sunQuan.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, sunQuan.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, sunQuan.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, sunQuan.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, sunQuan.getHitPoint()) + "th");
        }else if(general.getValue().equals("Zhang Zhao")){
            generalPhoto.setImage(new Image("/img/zhangZhao.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, zhangZhao.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, zhangZhao.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, zhangZhao.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, zhangZhao.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, zhangZhao.getHitPoint()) + "th");
        }else if(general.getValue().equals("Zhou Yu")){
            generalPhoto.setImage(new Image("/img/zhouYu.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, zhouYu.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, zhouYu.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, zhouYu.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, zhouYu.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, zhouYu.getHitPoint()) + "th");
        }else if(general.getValue().equals("Lu Su")){
            generalPhoto.setImage(new Image("/img/luSu.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, luSu.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, luSu.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, luSu.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, luSu.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, luSu.getHitPoint()) + "th");
        }else if(general.getValue().equals("Tai Shi Ci")){
            generalPhoto.setImage(new Image("/img/taiShiCi.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, taiShiCi.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, taiShiCi.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, taiShiCi.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, taiShiCi.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, taiShiCi.getHitPoint()) + "th");
        }else if(general.getValue().equals("Xiao Qiao")){
            generalPhoto.setImage(new Image("/img/xiaoQiao.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, xiaoQiao.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, xiaoQiao.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, xiaoQiao.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, xiaoQiao.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, xiaoQiao.getHitPoint()) + "th");
        }else if(general.getValue().equals("Da Qiao")){
            generalPhoto.setImage(new Image("/img/daQiao.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, daQiao.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, daQiao.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, daQiao.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, daQiao.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, daQiao.getHitPoint()) + "th");
        }else if(general.getValue().equals("Zhou Tai")){
            generalPhoto.setImage(new Image("/img/zhouTai.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, zhouTai.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, zhouTai.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, zhouTai.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, zhouTai.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, zhouTai.getHitPoint()) + "th");
        }else if(general.getValue().equals("Gan Ning")){
            generalPhoto.setImage(new Image("/img/ganNing.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, ganNing.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, ganNing.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, ganNing.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, ganNing.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, ganNing.getHitPoint()) + "th");
        }else if(general.getValue().equals("Lu Meng")){
            generalPhoto.setImage(new Image("/img/luMeng.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, luMeng.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, luMeng.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, luMeng.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, luMeng.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, luMeng.getHitPoint()) + "th");
        }else{
            generalPhoto.setImage(new Image("/img/huangGai.png"));
            strengthDisplay.setText("Strength: " + strengthBS(strength, huangGai.getStrength()) + "th");
            leadershipDisplay.setText("Leadership: " + leadershipBS(leadership, huangGai.getLeadership()) + "th");
            politicDisplay.setText("Politic: " + politicBS(politic, huangGai.getPolitic()) + "th");
            intelligenceDisplay.setText("Intelligence: " + intelligenceBS(intelligence, huangGai.getIntelligence()) + "th");
            hitPointDisplay.setText("Hit Point: " + hitPointBS(hitPoint, huangGai.getHitPoint()) + "th");
        }
        
    }
    public void back(ActionEvent event){
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
    public void next(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/SuggestGeneral.fxml"));
        Scene scene = new Scene(root,760,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Suggest Generals in each field");
        primaryStage.show();
    }
    
    public static int strengthBS(Integer[] arr, Integer key){
        Arrays.sort(arr, Comparator.reverseOrder());
        int start=0;
        int end = arr.length-1;
        
        while(start<=end){
            int mid = start + (end - start)/2;
            
            if(Objects.equals(arr[mid], key)){
                return mid+1;
            }else if(arr[mid] > key){
                start = mid+1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
    
    public static int intelligenceBS(Integer[] arr, Integer key){
        Arrays.sort(arr, Comparator.reverseOrder());
        int start=0;
        int end = arr.length-1;
        
        while(start<=end){
            int mid = start + (end - start)/2;
            
            if(Objects.equals(arr[mid], key)){
                return mid+1;
            }else if(arr[mid] > key){
                start = mid+1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
    
    public static int leadershipBS(Integer[] arr, Integer key){
        Arrays.sort(arr, Comparator.reverseOrder());
        int start=0;
        int end = arr.length-1;
        
        while(start<=end){
            int mid = start + (end - start)/2;
            
            if(Objects.equals(arr[mid], key)){
                return mid+1;
            }else if(arr[mid] > key){
                start = mid+1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
    
    public static int politicBS(Integer[] arr, Integer key){
        Arrays.sort(arr, Comparator.reverseOrder());
        int start=0;
        int end = arr.length-1;
        
        while(start<=end){
            int mid = start + (end - start)/2;
            
            if(Objects.equals(arr[mid], key)){
                return mid+1;
            }else if(arr[mid] > key){
                start = mid+1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
    
    public static int hitPointBS(Integer[] arr, Integer key){
        Arrays.sort(arr, Comparator.reverseOrder());
        int start=0;
        int end = arr.length-1;
        
        while(start<=end){
            int mid = start + (end - start)/2;
            
            if(Objects.equals(arr[mid], key)){
                return mid+1;
            }else if(arr[mid] > key){
                start = mid+1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
}
