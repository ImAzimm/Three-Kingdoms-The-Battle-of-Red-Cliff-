/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.Random;
import java.util.Stack;

public class DynamicStrawmen{
    private int num;
    private Random rng = new Random();
    Stack<Integer> numStrawmen = new Stack<>();
    Stack<Integer> efficiency = new Stack<>();

    public DynamicStrawmen(int num) {
        this.num = num;
        int percentage = 100 / 3;
        for(int i=0 ; i<3 ; i++){
            if(i == 2)
                efficiency.push(100);
            else{
                efficiency.push(rng.nextInt(percentage-5, percentage+1));
                percentage += 100/3;
            }
            numStrawmen.push(num * efficiency.peek() / 100);
        }
    }

    public DynamicStrawmen(int num, int usage) {
        this.num = num;
        int percentage = 100 / usage;
        for(int i=0 ; i<usage ; i++){
            if(i == usage-1)
                efficiency.push(100);
            else {
                efficiency.push(rng.nextInt(percentage-5, percentage+1));
                percentage += 100/usage;
            }
            numStrawmen.push(num * efficiency.peek() / 100);
        }
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
