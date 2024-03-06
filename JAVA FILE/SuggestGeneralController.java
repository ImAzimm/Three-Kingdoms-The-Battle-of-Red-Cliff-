/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class SuggestGeneralController implements Initializable {

    @FXML
    public ComboBox<String> ability;
    @FXML
    public ComboBox<String> level;
    @FXML
    private Button search;
    @FXML
    private Button backTree;
    @FXML
    private Button close;
    @FXML
    private Label generalDisplay;
    @FXML
    private Label levelDisplay;
    @FXML
    private ImageView general1;
    @FXML
    private ImageView general2;
    @FXML
    private ImageView general3;
    
    ObservableList<String> abilityList = FXCollections.observableArrayList("Strength","Leadership","Intelligence","Politic","Hit Point");
    ObservableList<String> levelList = FXCollections.observableArrayList("S","A","B","C");

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ability.setItems(abilityList);
        level.setItems(levelList);
        levelDisplay.setText("S level: sum of ability >= 250\nA level: sum of ability >= 220\nB level: sum of ability >= 190\nC level: sum of ability <= 190");
    }    
    
    public void search(ActionEvent event){
        General[] generals = {sunQuan, zhangZhao, zhouYu, xuSheng, zhuGeJin, luSu, taiShiCi, xiaoQiao, daQiao, zhouTai, ganNing, luMeng, huangGai};
        if(ability.getValue()==null){
            generalDisplay.setText("Please select ability");
            return;
        }
        if(level.getValue()==null){
            generalDisplay.setText("Please select level");
            return;
        }
        List<General> team = formTeam(generals, ability.getValue(), level.getValue());
        StringBuilder teamNames = new StringBuilder();
        int sum=0;
        for (General general : team) {
            teamNames.append(general.getName()).append(", ");
            sum += getAbility(general, ability.getValue());
        }
        String teamDisplay = "Team: " + teamNames.toString().replaceAll(", $", "");
        generalDisplay.setText(teamDisplay + "\nSum of ability: " + sum);
        setPhoto(team.get(0).getName(), general1);
        setPhoto(team.get(1).getName(), general2);
        setPhoto(team.get(2).getName(), general3);
    }
    
    public static List<General> formTeam(General[] generals, String ability, String level){
        General[] sortedGenerals = Arrays.copyOf(generals, generals.length);
        switch (ability) {
            case "Politic":
                Arrays.sort(sortedGenerals, Comparator.comparingInt(General::getPolitic).reversed());
                break;
            case "Leadership":
                Arrays.sort(sortedGenerals, Comparator.comparingInt(General::getLeadership).reversed());
                break;
            case "Strength":
                Arrays.sort(sortedGenerals, Comparator.comparingInt(General::getStrength).reversed());
                break;
            case "Intelligence":
                Arrays.sort(sortedGenerals, Comparator.comparingInt(General::getIntelligence).reversed());
                break;
            case "Hit Point":
                Arrays.sort(sortedGenerals, Comparator.comparingInt(General::getHitPoint).reversed());
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + ability);
        }
        
        List<General> team = new ArrayList<>();
        int sum=0;
        int member=0;
        int times=0;
        if(level.equals("S")){
            for(int i=0; i<sortedGenerals.length && member<3; i++){
                sum += getAbility(sortedGenerals[i], ability);
                member++;
                team.add(sortedGenerals[i]);
            }
            return team;
        }else if(level.equals("A")){
            Integer[] arr = getArr(sortedGenerals, ability);
            int mid = binarySearch(arr, 83);
            for(int i=mid-1; member<3; i++){
                sum += getAbility(sortedGenerals[i], ability);
                team.add(sortedGenerals[i]);
                member++;
            }
            if(sum>=220 && sum<250){
                return team;
            }
            while(sum>=250){
                sum=0;
                member=0;
                team.clear();
                for(int i=mid+times; member<3; i++){
                    sum += getAbility(sortedGenerals[i], ability);
                    team.add(sortedGenerals[i]);
                    member++;
                } 
                times++;
            }
            return team;
        }else if(level.equals("B")){
            Integer[] arr = getArr(sortedGenerals, ability);
            int mid = binarySearch(arr, 73);
            for(int i=mid-1; member<3; i++){
                sum += getAbility(sortedGenerals[i], ability);
                team.add(sortedGenerals[i]);
                member++;
            }
            if(sum>=190 && sum<220){
                return team;
            }
            while(sum>=220){
                sum=0;
                member=0;
                team.clear();
                for(int i=mid+times; member<3; i++){
                    sum += getAbility(sortedGenerals[i], ability);
                    team.add(sortedGenerals[i]);
                    member++;
                } 
                times++;
            }
            return team;
        }else{
            Integer[] arr = getArr(sortedGenerals, ability);
            int mid = binarySearch(arr, 63);
            for(int i=mid-1; member<3; i++){
                sum += getAbility(sortedGenerals[i], ability);
                team.add(sortedGenerals[i]);
                member++;
            }
            if(sum>=0 && sum<190){
                return team;
            }
            while(sum>=190){
                sum=0;
                member=0;
                team.clear();
                for(int i=mid+times; member<3; i++){
                    sum += getAbility(sortedGenerals[i], ability);
                    team.add(sortedGenerals[i]);
                    member++;
                } 
                times++;
            }
            return team;
        }
    }
    
    public static Integer getAbility(General general, String ability){
        switch(ability){
            case "Politic":
                return general.getPolitic();
            case "Leadership":
                return general.getLeadership();
            case "Strength":
                return general.getStrength();
            case "Intelligence":
                return general.getIntelligence();
            case "Hit Point":
                return general.getHitPoint();
            default:
                throw new IllegalArgumentException("Invalid field: " + ability);
        }
    }
    
    public static int binarySearch(Integer[] arr, int key) {
        int start=0;
        int end = arr.length-1;
        int mid=0;
        while(start<=end){
            mid = start + (end - start)/2;
            
            if(Objects.equals(arr[mid], key)){
                return mid;
            }else if(arr[mid] > key){
                start = mid+1;
            }else{
                end = mid - 1;
            }
        }
        return mid-1;
    }
    
    public static Integer[] getArr(General[] general, String ability){
        Integer[] arr = new Integer[13];
        
        for(int i=0; i<general.length; i++){
            switch (ability) {
                case "Strength":
                    arr[i] = general[i].getStrength();
                    break;
                case "Intelligence":
                    arr[i] = general[i].getIntelligence();
                    break;
                case "Politic":
                    arr[i] = general[i].getPolitic();
                    break;
                case "Leadership":
                    arr[i] = general[i].getLeadership();
                    break;
                default:
                    arr[i] = general[i].getHitPoint();
                    break;
            }
        }
        return arr;
    }
    
    public static void setPhoto(String name, ImageView photo){
        if(name.equals("Xu Sheng")){
            photo.setImage(new Image("/img/xuSheng.png"));
        }else if(name.equals("Zhu Ge Jin")){
            photo.setImage(new Image("/img/zhuGeJin.png"));
        }else if(name.equals("Zhou Yu")){
            photo.setImage(new Image("/img/zhouYu.png"));
        }else if(name.equals("Zhang Zhao")){
            photo.setImage(new Image("/img/zhangZhao.png"));
        }else if(name.equals("Sun Quan")){
            photo.setImage(new Image("/img/sunQuan.png"));
        }else if(name.equals("Lu Su")){
            photo.setImage(new Image("/img/luSu.png"));
        }else if(name.equals("Tai Shi Ci")){
            photo.setImage(new Image("/img/taiShiCi.png"));
        }else if(name.equals("Xiao Qiao")){
            photo.setImage(new Image("/img/xiaoQiao.png"));
        }else if(name.equals("Da Qiao")){
            photo.setImage(new Image("/img/daQiao.png"));
        }else if(name.equals("Zhou Tai")){
            photo.setImage(new Image("/img/zhouTai.png"));
        }else if(name.equals("Gan Ning")){
            photo.setImage(new Image("/img/ganNing.png"));
        }else if(name.equals("Lu Meng")){
            photo.setImage(new Image("/img/luMeng.png"));
        }else{
            photo.setImage(new Image("/img/huangGai.png"));
        }
    }
    
    public void close(ActionEvent event){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    
    public void backTree(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/Tree.fxml"));
        Scene scene = new Scene(root,740,565);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wu Kingdom Tree Table View");
        primaryStage.show();
    }
}
