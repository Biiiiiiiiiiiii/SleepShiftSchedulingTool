/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sleepshift;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class SleepShift {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Key to a healthy lifestyle: SLEEP\nSelect your function: \n1.Sleep Schedule Planner\n2.Exercise Schedule Planner\n3.Nutrition guide\n4.Show my schedule today");
        int func = s.nextInt();
        switch (func) {
            case 1:
                Sleep sl = new Sleep();
                break;
            case 2:
                ExercisePlanner ex = new ExercisePlanner();
                break;
            case 3:
                NutritionPlanner n = new NutritionPlanner();
                break;
            case 4:
                AstronautDailySchedule ads = new AstronautDailySchedule(); 
                break;
            default:
                break;
        }
    }
    
}
