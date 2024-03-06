/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ArrangementController implements Initializable {
    
    @FXML
    public ComboBox<String> ability;
    @FXML
    private Button Ascendingsort;
    @FXML
    private Button Descendingsort;
    @FXML
    private Button next;
    @FXML
    private TableView<General> abilityTable;
    @FXML
    private TableColumn<General, String> name;
    @FXML
    private TableColumn<General, String> armyType;
    @FXML
    private TableColumn<General, Integer> leadership;
    @FXML
    private TableColumn<General, Integer> intelligence;
    @FXML
    private TableColumn<General, Integer> politic;
    @FXML
    private TableColumn<General, Integer> hitPoint;
    @FXML
    private TableColumn<General, Integer> strength;
    
    
    ObservableList<String> list = FXCollections.observableArrayList("Leadership","Strength","Intelligence","Political Skill","Hit Point");
    public ObservableList<General> table = FXCollections.observableArrayList(
            new General("Sun Quan", "Cavalry", 96, 98, 72, 77, 95),
            new General("Zhang Zhao", "Archer", 22, 80, 89, 99, 60),
            new General("Zhou Yu", "Cavalry", 80, 86, 87, 80, 90),
            new General("Xu Sheng", "Archer", 90, 78, 72, 40, 94),
            new General("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71),
            new General("Lu Su", "Infantry", 43, 87, 84, 88, 53),
            new General("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97),
            new General("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34),
            new General("Da Qiao", "Cavalry", 39, 62, 90, 62, 41),
            new General("Zhou Tai", "Infantry", 92, 89, 72, 43, 99),
            new General("Gan Ning", "Archer", 98, 92, 45, 23, 97),
            new General("Lu Meng", "Cavalry", 70, 77, 93, 83, 88),
            new General("Huang Gai", "Infantry", 83, 98, 72, 42, 89)
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ability.setItems(list);
        name.setCellValueFactory(new PropertyValueFactory<General, String>("name"));
        armyType.setCellValueFactory(new PropertyValueFactory<General, String>("armyType"));
        leadership.setCellValueFactory(new PropertyValueFactory<General, Integer>("leadership"));
        politic.setCellValueFactory(new PropertyValueFactory<General, Integer>("politic"));
        hitPoint.setCellValueFactory(new PropertyValueFactory<General, Integer>("hitPoint"));
        strength.setCellValueFactory(new PropertyValueFactory<General, Integer>("strength"));
        intelligence.setCellValueFactory(new PropertyValueFactory<General, Integer>("intelligence"));
        abilityTable.setItems(table);
    }
    
    public void AscendingSort(ActionEvent event){
        String value = ability.getValue();
        AscendingInsertionSort(table, value);
        abilityTable.setItems(table);
    }
    
    public void DescendingSort(ActionEvent event){
        String value = ability.getValue();
        DescendingSelectionSort(table, value);
        abilityTable.setItems(table);
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
    
    public static void AscendingInsertionSort(ObservableList<General> general, String value){
        if(value.equals("Leadership")){
            for(int i=1; i<general.size(); i++){
                int key = general.get(i).getLeadership();
                General temp = general.get(i);
                int j = i-1;
                while(j>=0 && general.get(j).getLeadership()>key){
                    general.set(j+1, general.get(j));
                    j--;
                }
                general.set(j+1, temp);
            }
        }else if(value.equals("Strength")){
            for(int i=1; i<general.size(); i++){
                int key = general.get(i).getStrength();
                General temp = general.get(i);
                int j = i-1;
                while(j>=0 && general.get(j).getStrength()>key){
                    general.set(j+1, general.get(j));
                    j--;
                }
                general.set(j+1, temp);
            }
        } else if(value.equals("Intelligence")){
            for(int i=1; i<general.size(); i++){
                int key = general.get(i).getIntelligence();
                General temp = general.get(i);
                int j = i-1;
                while(j>=0 && general.get(j).getIntelligence()>key){
                    general.set(j+1, general.get(j));
                    j--;
                }
                general.set(j+1, temp);
            }
        } else if(value.equals("Political Skill")){
            for(int i=1; i<general.size(); i++){
                int key = general.get(i).getPolitic();
                General temp = general.get(i);
                int j = i-1;
                while(j>=0 && general.get(j).getPolitic()>key){
                    general.set(j+1, general.get(j));
                    j--;
                }
                general.set(j+1, temp);
            }
        } else if(value.equals("Hit Point")){
            for(int i=1; i<general.size(); i++){
                int key = general.get(i).getHitPoint();
                General temp = general.get(i);
                int j = i-1;
                while(j>=0 && general.get(j).getHitPoint()>key){
                    general.set(j+1, general.get(j));
                    j--;
                }
                general.set(j+1, temp);
            }
        }
    }
    
    public static void DescendingSelectionSort(ObservableList<General> general, String value) {
    if (value.equals("Leadership")) {
        for (int i = 0; i < general.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < general.size(); j++) {
                if (general.get(j).getLeadership() > general.get(maxIndex).getLeadership()) {
                    maxIndex = j;
                }
            }
            General temp = general.get(i);
            general.set(i, general.get(maxIndex));
            general.set(maxIndex, temp);
        }
    } else if (value.equals("Strength")) {
        for (int i = 0; i < general.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < general.size(); j++) {
                if (general.get(j).getStrength() > general.get(maxIndex).getStrength()) {
                    maxIndex = j;
                }
            }
            General temp = general.get(i);
            general.set(i, general.get(maxIndex));
            general.set(maxIndex, temp);
        }
    } else if (value.equals("Intelligence")) {
        for (int i = 0; i < general.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < general.size(); j++) {
                if (general.get(j).getIntelligence() > general.get(maxIndex).getIntelligence()) {
                    maxIndex = j;
                }
            }
            General temp = general.get(i);
            general.set(i, general.get(maxIndex));
            general.set(maxIndex, temp);
        }
    } else if (value.equals("Political Skill")) {
        for (int i = 0; i < general.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < general.size(); j++) {
                if (general.get(j).getPolitic() > general.get(maxIndex).getPolitic()) {
                    maxIndex = j;
                }
            }
            General temp = general.get(i);
            general.set(i, general.get(maxIndex));
            general.set(maxIndex, temp);
        }
    } else if (value.equals("Hit Point")) {
        for (int i = 0; i < general.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < general.size(); j++) {
                if (general.get(j).getHitPoint() > general.get(maxIndex).getHitPoint()) {
                    maxIndex = j;
                }
            }
            General temp = general.get(i);
            general.set(i, general.get(maxIndex));
            general.set(maxIndex, temp);
        }
    }
}
    
    
}


