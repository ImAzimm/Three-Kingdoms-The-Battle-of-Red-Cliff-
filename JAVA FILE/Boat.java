/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class Boat{
    private Strawmen left, right, front, back;
    int numOfStrawman;

    public Boat() {
    }

    public Boat(int left, int right, int front, int back) {
        this.left = new Strawmen(left);
        this.right = new Strawmen(right);
        this.front = new Strawmen(front);
        this.back = new Strawmen(back);
    }

    public void setLeft(int left) {
        this.left = new Strawmen(left);
    }

    public void setRight(int right) {
        this.right = new Strawmen(right);
    }

    public void setFront(int front) {
        this.front = new Strawmen(front);
    }

    public void setBack(int back) {
        this.back = new Strawmen(back);
    }

    public int Serang(String direction, int arrow){
        if(direction.equals("left")) {
            return arrow * numOfStrawman / 100;
        }
        else if(direction.equals("right")){
            return arrow * numOfStrawman / 100;
        }
        else if(direction.equals("front")){
            return arrow * numOfStrawman / 100;
        }
        else if(direction.equals("back")){
            return arrow * numOfStrawman / 100;
        }
        else
            return 0;
    }
    
    public String procedure(String direction, int arrow){
        StringBuilder sb = new StringBuilder();
        if(direction.equals("left")) {
            numOfStrawman = left.getStrawmenLeft();
            sb.append("Direction of Attack: Left        Wave arrow: " + arrow + " arrows\n");
            sb.append("Number of strawmen: " + numOfStrawman + "        Efficiency: " + left.getEfficiency() + "%\n");
            sb.append(numOfStrawman + " strawmen successfully collect " + (arrow * numOfStrawman / 100) + " arrows\n");
            return sb.toString();
        }else if(direction.equals("right")){
            numOfStrawman = right.getStrawmenLeft();
            sb.append("Direction of Attack: Right        Wave arrow: " + arrow + " arrows\n");
            sb.append("Number of strawmen: " + numOfStrawman + "        Efficiency: " + right.getEfficiency() + "%\n");
            sb.append(numOfStrawman + " strawmen successfully collect " + (arrow * numOfStrawman / 100) + " arrows\n");
            return sb.toString();
        }else if(direction.equals("front")){
            numOfStrawman = front.getStrawmenLeft();
            sb.append("Direction of Attack: Front        Wave arrow: " + arrow + " arrows\n");
            sb.append("Number of strawmen: " + numOfStrawman + "        Efficiency: " + front.getEfficiency() + "%\n");
            sb.append(numOfStrawman + " strawmen successfully collect " + (arrow * numOfStrawman / 100) + " arrows\n");
            return sb.toString();
        }else if(direction.equals("back")){
            numOfStrawman = back.getStrawmenLeft();
            sb.append("Direction of Attack: Back        Wave arrow: " + arrow + " arrows\n");
            sb.append("Number of strawmen: " + numOfStrawman + "        Efficiency: " + back.getEfficiency() + "%\n");
            sb.append(numOfStrawman + " strawmen successfully collect " + (arrow * numOfStrawman / 100) + " arrows\n");
            return sb.toString();
        }else
            return null;
    }
}
