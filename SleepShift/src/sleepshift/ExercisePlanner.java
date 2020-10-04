/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sleepshift;

import java.util.*;
import java.io.*;

/**
 *
 * @author user
 */
public class ExercisePlanner {

    private int i = 0, n = 0,minutes;
    private int weight;
    private ArrayList<Double> METList = new ArrayList();
    private String type, filename;
    private Scanner s = new Scanner(System.in);
    private Scanner in;
    private Random r = new Random();
    private ArrayList<String> activityList = new ArrayList();

    public ExercisePlanner() {
        System.out.println("You are in the mood for: \n1. Light exercise\n2. Moderate Exercise\n3. Vigorous exercise");
        int input = s.nextInt();
        switch (input) {
            case 1:
                light();
                break;
            case 2:
                moderate();
                break;
            case 3:
                vigorous();
                break;
            default:
                break;
        }
        try {
            in = new Scanner(new FileInputStream(filename));
            while (in.hasNextLine()) {
                type = in.nextLine();
                String[] word = type.split(":");
                METList.add(Double.parseDouble(word[0]));
                activityList.add(word[1]);

                ++i;
            }
        } catch (Exception e) {
        }
        System.out.println("Your weight(kg): ");
        weight = s.nextInt();
        System.out.println("How long do you plan to exercise(min)? ");
        minutes = s.nextInt();
        System.out.println("These are the activities recommended for you: ");
        String str = "";
        int[] temp = new int[3];
        for (int k = 0; k < temp.length; k++) {
            n = r.nextInt(activityList.size());
            if(temp[k] == n){
                if(n==activityList.size()-1){
                    n--;
                }
                n++;
            }
            temp[k] = n;
            str += (k + 1) + ". " + activityList.get(n) + String.format(" --> Total %.2f calories burned\n", caloriesBurned(METList.get(n), weight));

        }
        System.out.print(str);
    }

    private void light() {
        filename = "MET_light.txt";
    }

    private void moderate() {
        filename = "MET_moderate.txt";
    }

    private void vigorous() {
        filename = "MET_vigorous.txt";
    }

    /*private double caloriesPerHour(double value, int weight) {
        return value * weight;
    }*/
    private double caloriesBurned(double value,int weight){
        return (minutes*(value*3.5*weight)/200);
    }

}
/*2
20
F
160
55
3
1*/
