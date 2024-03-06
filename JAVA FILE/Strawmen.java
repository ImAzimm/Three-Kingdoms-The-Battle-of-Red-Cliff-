/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Random;
import java.util.Stack;

public class Strawmen{
    private int num;
    private Random rng = new Random();
    Stack<Integer> numStrawmen = new Stack<>();
    Stack<Integer> efficiency = new Stack<>();

    public Strawmen(int num) {
        efficiency.push(40);
        numStrawmen.push(num * efficiency.peek() / 100);
        efficiency.push(80);
        numStrawmen.push(num * efficiency.peek() / 100);
        efficiency.push(100);
        numStrawmen.push(num * efficiency.peek() / 100);
    }

    public int getStrawmenLeft() {
        if(numStrawmen.empty()){
            return 0;
        }
        return numStrawmen.pop();
    }

    public int getEfficiency() {
        if(efficiency.empty()){
            return 0;
        }
        return efficiency.pop();
    }
}
