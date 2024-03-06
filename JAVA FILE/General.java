/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class General {
    private final SimpleStringProperty name;
    private final SimpleStringProperty armyType;
    private final SimpleIntegerProperty strength;
    private final SimpleIntegerProperty leadership;
    private final SimpleIntegerProperty intelligence;
    private final SimpleIntegerProperty politic;
    private final SimpleIntegerProperty hitPoint;

    public General(String name, String armyType, Integer strength, Integer leadership, Integer intelligence, Integer politic, Integer hitPoint){
        super();
        this.name = new SimpleStringProperty(name);
        this.armyType = new SimpleStringProperty(armyType);
        this.strength = new SimpleIntegerProperty(strength);
        this.leadership = new SimpleIntegerProperty(leadership);
        this.intelligence = new SimpleIntegerProperty(intelligence);
        this.politic = new SimpleIntegerProperty(politic);
        this.hitPoint = new SimpleIntegerProperty(hitPoint);
    }

    public String getName(){
        return name.get();
    }
    public String getArmyType(){
        return armyType.get();
    }
    public Integer getStrength(){
        return strength.get();
    }
    public Integer getLeadership(){
        return leadership.get();
    }
    public Integer getIntelligence(){
        return intelligence.get();
    }
    public Integer getPolitic(){
        return politic.get();
    }
    public Integer getHitPoint(){
        return hitPoint.get();
    }
    public void setName(String name){
        this.name.set(name);
    }
    public void setArmyType(String armyType){
        this.armyType.set(armyType);
    }
    public void setStrength(Integer strength){
        this.strength.set(strength);
    }
    public void setIntelligence(Integer intelligence){
        this.intelligence.set(intelligence);
    }
    public void setPolitic(Integer politic){
        this.politic.set(politic);
    }
    public void setHitPoint(Integer hitPoint){
        this.hitPoint.set(hitPoint);
    }
    public void setLeadership(Integer leadership){
        this.leadership.set(leadership);
    }
    
}
