/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TreeController implements Initializable {
    
    @FXML
    private TreeTableView<WuKingdom> tableView;
    @FXML
    private TreeTableColumn<WuKingdom,String> name;
    @FXML
    private TreeTableColumn<WuKingdom,String> armyType;
    @FXML
    private TreeTableColumn<WuKingdom,Number> strength;
    @FXML
    private TreeTableColumn<WuKingdom,Number> leadership;
    @FXML
    private TreeTableColumn<WuKingdom,Number> intelligence;
    @FXML
    private TreeTableColumn<WuKingdom,Number> politic;
    @FXML
    private TreeTableColumn<WuKingdom,Number> hitPoint;
    @FXML
    private Button close;
    @FXML
    private Button sort;
    @FXML
    private Button binarySort;
    
    public void close(ActionEvent event){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    
    public void sort(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/Arrangement.fxml"));
        Scene scene = new Scene(root,770,570);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sorting table");
        primaryStage.show();
    }
    
    public void binarySort(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/assignment/BinarySearch.fxml"));
        Scene scene = new Scene(root,755,560);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Binary search page");
        primaryStage.show();
    }
    
    TreeItem<WuKingdom> zhangZhao = new TreeItem<>(new WuKingdom("Zhang Zhao", "Archer", 22, 80, 89, 99, 60));
    TreeItem<WuKingdom> zhouYu = new TreeItem<>(new WuKingdom("Zhou Yu", "Cavalry", 80, 86, 97, 80, 90));
    TreeItem<WuKingdom> sunQuan = new TreeItem<>(new WuKingdom("Sun Quan", "Cavalry", 96, 98, 72, 77, 95));
    TreeItem<WuKingdom> xuSheng = new TreeItem<>(new WuKingdom("Xu Sheng", "Archer", 90, 78, 72, 40, 94));
    TreeItem<WuKingdom> zhuGeJin = new TreeItem<>(new WuKingdom("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71));
    TreeItem<WuKingdom> luSu = new TreeItem<>(new WuKingdom("Lu Su", "Infantry", 43, 87, 84, 88, 53));
    TreeItem<WuKingdom> taiShiCi = new TreeItem<>(new WuKingdom("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97));
    TreeItem<WuKingdom> xiaoQiao = new TreeItem<>(new WuKingdom("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34));
    TreeItem<WuKingdom> daQiao = new TreeItem<>(new WuKingdom("Da Qiao", "Cavalry", 39, 62, 90, 62, 41));
    TreeItem<WuKingdom> zhouTai = new TreeItem<>(new WuKingdom("Zhou Tai", "Infantry", 92, 89, 72, 43, 99));
    TreeItem<WuKingdom> ganNing = new TreeItem<>(new WuKingdom("Gan Ning", "Archer", 98, 92, 45, 23, 97));
    TreeItem<WuKingdom> luMeng = new TreeItem<>(new WuKingdom("Lu Meng", "Cavalry", 70, 77, 93, 83, 88));
    TreeItem<WuKingdom> huangGai = new TreeItem<>(new WuKingdom("Huang Gai", "Infantry", 83, 98, 72, 42, 89));
    
    ObservableList<TreeItem<WuKingdom>> general = FXCollections.observableArrayList(
            xuSheng,
            zhuGeJin,
            luSu,
            taiShiCi,
            xiaoQiao,
            daQiao,
            zhouTai,
            ganNing,
            luMeng,
            huangGai
    );
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image emperor=new Image("/img/emperor.png",20,20,true,true);
        Image generalIcon=new Image("/img/general.png",20,20,true,true);
        Image boy=new Image("/img/boy.png",20,20,true,true);
        Image girl=new Image("/img/girl.png",20,20,true,true);
        
        
        sunQuan.getChildren().setAll(zhangZhao, zhouYu);
        sunQuan.setGraphic(new ImageView(emperor));
        sunQuan.setExpanded(true);
        zhangZhao.setGraphic(new ImageView(generalIcon));
        zhouYu.setGraphic(new ImageView(generalIcon));
        for(int i=0; i<general.size(); i++){
            if(general.get(i).getValue().getIntelligenceProperty().greaterThan(general.get(i).getValue().getStrengthProperty()).get()){
                zhangZhao.getChildren().add(general.get(i));
                general.get(i).setGraphic(new ImageView(boy));
                zhangZhao.setExpanded(true);
            }else{
                zhouYu.getChildren().add(general.get(i));
                general.get(i).setGraphic(new ImageView(boy));
                zhouYu.setExpanded(true);
            }
        }
        xiaoQiao.setGraphic(new ImageView(girl));
        daQiao.setGraphic(new ImageView(girl));
        name.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<WuKingdom, String> p) -> p.getValue().getValue().getNameProperty());
        armyType.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<WuKingdom, String> p) -> p.getValue().getValue().getArmyTypeProperty());
        strength.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<WuKingdom, Number> p) -> p.getValue().getValue().getStrengthProperty());
        leadership.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<WuKingdom, Number> p) -> p.getValue().getValue().getLeadershipProperty());
        intelligence.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<WuKingdom, Number> p) -> p.getValue().getValue().getIntelligenceProperty());
        politic.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<WuKingdom, Number> p) -> p.getValue().getValue().getPoliticProperty());
        hitPoint.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<WuKingdom, Number> p) -> p.getValue().getValue().getHitPointProperty());
        tableView.setRoot(sunQuan);
        tableView.setShowRoot(true);
        
    }    
    
    class WuKingdom{
        SimpleStringProperty nameProperty;
        SimpleStringProperty armyTypeProperty;
        SimpleIntegerProperty strengthProperty;
        SimpleIntegerProperty leadershipProperty;
        SimpleIntegerProperty intelligenceProperty;
        SimpleIntegerProperty politicProperty;
        SimpleIntegerProperty hitPointProperty;
        
        public WuKingdom(String name, String armyTpe, Integer strength, Integer leadership, Integer intelligence, Integer politic, Integer hitPoint){
            this.nameProperty = new SimpleStringProperty(name);
            this.armyTypeProperty = new SimpleStringProperty(armyTpe);
            this.strengthProperty = new SimpleIntegerProperty(strength);
            this.leadershipProperty = new SimpleIntegerProperty(leadership);
            this.intelligenceProperty = new SimpleIntegerProperty(intelligence);
            this.politicProperty = new SimpleIntegerProperty(politic);
            this.hitPointProperty = new SimpleIntegerProperty(hitPoint);
        }
        public SimpleStringProperty getNameProperty(){
            return nameProperty;
        }
        public SimpleStringProperty getArmyTypeProperty(){
            return armyTypeProperty;
        }
        public SimpleIntegerProperty getStrengthProperty(){
            return strengthProperty;
        }
        public SimpleIntegerProperty getLeadershipProperty(){
            return leadershipProperty;
        }
        public SimpleIntegerProperty getIntelligenceProperty(){
            return intelligenceProperty;
        }
        public SimpleIntegerProperty getPoliticProperty(){
            return politicProperty;
        }
        public SimpleIntegerProperty getHitPointProperty(){
            return hitPointProperty;
        }
        public void setName(SimpleStringProperty name){
            this.nameProperty = nameProperty;
        }
        public void setArmyType(SimpleStringProperty armyType){
            this.armyTypeProperty = armyTypeProperty;
        }
        public void setStrengthProperty(SimpleIntegerProperty strength){
            this.strengthProperty = strengthProperty;
        }
        public void setLeadershipProperty(SimpleIntegerProperty leadership){
            this.leadershipProperty = leadershipProperty;
        }
        public void setIntelligenceProperty(SimpleIntegerProperty intelligence){
            this.intelligenceProperty = intelligenceProperty;
        }
        public void setPoliticProperty(SimpleIntegerProperty politic){
            this.politicProperty = politicProperty;
        }
        public void setHitPointProperty(SimpleIntegerProperty hitPoint){
            this.hitPointProperty = hitPointProperty;
        }
    }
}
