/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class DynamicBoat{
    private DynamicStrawmen left, right, front, back;
    int numOfStrawman;

    public DynamicBoat() {
    }

    public DynamicBoat(int left, int right, int front, int back) {
        this.left = new DynamicStrawmen(left);
        this.right = new DynamicStrawmen(right);
        this.front = new DynamicStrawmen(front);
        this.back = new DynamicStrawmen(back);
    }

    public DynamicBoat(int left, int right, int front, int back, int usage) {
        this.left = new DynamicStrawmen(left, usage);
        this.right = new DynamicStrawmen(right, usage);
        this.front = new DynamicStrawmen(front, usage);
        this.back = new DynamicStrawmen(back, usage);
    }

    public void setLeft(int left) {
        this.left = new DynamicStrawmen(left);
    }

    public void setRight(int right) {
        this.right = new DynamicStrawmen(right);
    }

    public void setFront(int front) {
        this.front = new DynamicStrawmen(front);
    }

    public void setBack(int back) {
        this.back = new DynamicStrawmen(back);
    }

    public void setLeft(int left, int usage) {
        this.left = new DynamicStrawmen(left, usage);
    }

    public void setRight(int right, int usage) {
        this.right = new DynamicStrawmen(right, usage);
    }

    public void setFront(int front, int usage) {
        this.front = new DynamicStrawmen(front, usage);
    }

    public void setBack(int back, int usage) {
        this.back = new DynamicStrawmen(back, usage);
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
