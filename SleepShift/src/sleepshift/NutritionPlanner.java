/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sleepshift;

import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 *
 * @author user
 */
public class NutritionPlanner {

    private int weight, height, age;
    private double activityFactor,carb,fat,protein;
    private boolean isMale = false, isInSpace = false;
    private Scanner s = new Scanner(System.in);
    private Scanner in;
    private String fileDir = "D:\\UM stuff\\NASA 2020\\SleepShift\\Food List\\";
    private Random r = new Random();
    
    public NutritionPlanner() {
        System.out.println("Are you an astronaut up in space? (Y/N):");
        String input = s.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            isInSpace = true;
        }
        System.out.println("Age: ");
        age = s.nextInt();
        System.out.println("Gender (M/F): ");
        if (s.next().equals('M')) {
            isMale = true;
        }
        System.out.println("Height in cm: ");
        height = s.nextInt();
        System.out.println("Weight in kg: ");
        weight = s.nextInt();
        if (isInSpace) {
            double energyPerDay = 0;
            if (isMale) {
                energyPerDay = (66.5 + (13.8 * weight) + (5 * height) - (6.8 * age)) * 1.6;
            } else {
                energyPerDay = (655.1 + (9.6 * weight) + (1.9 * height) - (4.7 * age)) * 1.6;
            }
            System.out.printf("Recommended total energy needed per day: %.2f\n", energyPerDay);
            proportion(energyPerDay);
        } else {
            System.out.println("In a scale of 1 to 5, rate how active are you (1=Sedentary),(5=Extra active): ");
            int temp = s.nextInt();
            getActivityFactor(temp);
            System.out.printf("Recommended total calorie intake per day: %.2f\n", (TDEE(isMale)));
            proportion(TDEE(isMale));
        }
        
    }

    private void getActivityFactor(int temp) {
        switch (temp) {
            case 1:
                activityFactor = 1.4;
                break;
            case 2:
                activityFactor = 1.6;
                break;
            case 3:
                activityFactor = 1.8;
                break;
            case 4:
                activityFactor = 2.0;
                break;
            case 5:
                activityFactor = 2.2;
                break;
            default:
                System.out.println("Invalid input");
        }

    }

    private double TDEE(boolean isMale) {
        double calPerDay = 0;
        if (isMale) {
            calPerDay = (10 * weight + 6.25 * height - 5 * age + 5) * activityFactor;
        } else {
            calPerDay = (10 * weight + 6.25 * height - 5 * age - 161) * activityFactor;
        }
        return calPerDay;
    }
    private void proportion(double total){
        carb = total*.55;
        fat = total*.3;
        protein = total*.15;
        System.out.printf("It is recommended that your meal consists of %.2fcal carbohydrates, %.2fcal fat and %.2fcal protein.",carb,fat,protein);
    }
    
}
