/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sleepshift;

import java.time.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
/**
 *
 * @author user
 */
public class Sleep {
    
    private int mode1,mode2,minute,hour,age;
    private boolean enough=true,blueLightFilter=false;
    private Scanner s = new Scanner(System.in);
    private Date sleepDate=null;
    private Date wakeDate=null;
    private ZonedDateTime departure,arrival;
    
    public Sleep() {
        System.out.println("Enter your age: ");
        age = s.nextInt();
        System.out.println("Select mode: \n1.Normal\n2.Travel\n3.Out in space");
        mode1=s.nextInt();
        if(mode1==1){
            normal();
        }
        else if(mode1==2){
            travel();
        }
        else if(mode1==3){
            space();
        }
        
    }
    private void normal(){
        System.out.println("Find your: \n1.Sleeping time\n2.Waking time\nOR\n3.Monitor sleep");
        mode2 = s.nextInt();
        if(mode2==1){
            wake();
        }
        else if(mode2==2){
            sleep();
        }
        else if(mode2==3){
            monitor();
            
        }
        
    }
    private void travel(){
        /*SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm");
        System.out.println("Enter date and time of departure (yyyy MM dd  HH mm)");
        String date = s.nextLine();
        String[]word = date.split(" ");
        int year=Integer.parseInt(word[0]);
        int month=Integer.parseInt(word[1]);
        int day=Integer.parseInt(word[2]);
        int hr=Integer.parseInt(word[3]);
        int min=Integer.parseInt(word[4]);
        LocalDateTime ldt = LocalDateTime.of(year,month,day,hr,min);
        Set<String> availableZoneIDs = ZoneId.getAvailableZoneIds();
        int n=1;
        ArrayList<String> z = new ArrayList();
        for(String zone:availableZoneIDs){
            System.out.println(n+". "+zone);
            z.add(zone);
            n++;
        }
        System.out.println("Choose your departure zone: ");
        int depart=s.nextInt();
        departure = ldt.atZone(ZoneId.of(z.get(depart-1)));
        System.out.println("Flight duration(HH MM): ");
        int flightHour = s.nextInt();
        int flightMin = s.nextInt();
        System.out.println("Choose you arrival zone: ");
        int arrive=s.nextInt();
        arrival = ldt.atZone(ZoneId.of(z.get(arrive-1))).plusHours(flightHour).plusMinutes(flightMin);
        System.out.println("Your departure time is "+df.format(departure));
        System.out.println("Your arrival time is "+df.format(arrival));*/
    }   
    private void sleep(){
        System.out.println("What time do you plan to sleep?(HH MM): ");
        hour = s.nextInt();
        minute=s.nextInt();
        LocalTime sleepTime = LocalTime.of(hour, minute);
        if(mode1==1){
            LocalTime cycle4 = sleepTime.plusMinutes(375);
        LocalTime cycle5 = sleepTime.plusMinutes(465);
        LocalTime cycle6 = sleepTime.plusMinutes(555);
        System.out.println("Recommended time to wake up: ");
        System.out.println(cycle4+"(4 sleep cycles)\t"+cycle5+"(5 sleep cycles)\t"+cycle6+"(6 sleep cycles)\t");
        System.out.println("Note: It takes about 15 minutes to fall asleep for the average human. \nA sleep cycle is about 90 minutes, and a good night's sleep comprises of 5-6 complete sleep cycles.");
        }
        else if(mode1==3){
            LocalTime goodSleep = sleepTime.plusMinutes(30).plusHours(8);
            LocalTime minSleep = sleepTime.plusHours(6);
            System.out.println("Recommended time to wake up: ");
            System.out.println(goodSleep+" (optimum)\t"+minSleep+" (minimum)\t");
            System.out.println("Note: Sleep schedules for most missions are 8.5 hours but an average astronaut sleeps 6 hours per day.");
        }
        
    }
    private void wake(){
        System.out.println("What time do you plan to wake up?(HH MM): ");
        hour = s.nextInt();
        minute=s.nextInt();
        LocalTime sleepTime = LocalTime.of(hour, minute);
        if(mode1==1){
            LocalTime cycle4 = sleepTime.minusMinutes(375);
        LocalTime cycle5 = sleepTime.minusMinutes(465);
        LocalTime cycle6 = sleepTime.minusMinutes(555);
        System.out.println("Recommended time to sleep: ");
        System.out.println(cycle4+"(4 sleep cycles)\t"+cycle5+"(5 sleep cycles)\t"+cycle6+"(6 sleep cycles)\t");
        System.out.println("Note: It takes about 15 minutes to fall asleep for the average human. \nA sleep cycle is about 90 minutes, and a good night's sleep comprises of 5-6 complete sleep cycles.");
        }
        else if(mode1==3){
            LocalTime goodSleep = sleepTime.minusMinutes(30).minusHours(8);
            LocalTime minSleep = sleepTime.minusHours(6);
            System.out.println("Recommended time to sleep: ");
            System.out.println(goodSleep+" (optimum)\t"+minSleep+" (minimum)\t");
            System.out.println("Note: Sleep schedules for most missions are 8.5 hours but an average astronaut sleeps 6 hours per day.");
        }
    }
    private void monitor(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        System.out.println("Press S to start monitoring your sleep ");
        String input = s.nextLine();
        while(!input.equalsIgnoreCase("e")){
            if(input.equalsIgnoreCase("s")){
            sleepDate = new Date();
            System.out.println("Press E when you wake up ");
        }
        input = s.nextLine();
        if(input.equalsIgnoreCase("e")){
            wakeDate = new Date();
        }
        }
        try{
            FileWriter fw = new FileWriter("history.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String cs = String.format("%25s%25s%40s\n",df.format(sleepDate),df.format(wakeDate),duration(sleepDate,wakeDate));
            pw.write(cs);
            pw.close();
            
        }catch(Exception e){
            System.out.println("File cannot be found.");
        }
        System.out.println("You slept from "+df.format(sleepDate)+" to "+df.format(wakeDate)+". Total duration of sleep is "+duration(sleepDate,wakeDate));
        if(!enough){
            System.out.println("You are lack of sleep last night. Please compensate for the sleep lost today by taking naps, melatonin or adjusting your schedule. ");
        }
        System.out.println("Please check \"history.txt\" for full sleeping history.");
    }
    private String duration(Date startDate, Date endDate){
        long difference = endDate.getTime()-startDate.getTime();
        long millisec = 1000;
        long millimin = millisec*60;
        long millihour = millimin*60;
        long elapsedHours = difference/millihour;
        difference%=millihour;
        long elapsedMins = difference/millimin;
        difference%=millimin;
        long elapsedSecs = difference/millisec;
        String str = String.format("%d hours, %d minutes, %d seconds",elapsedHours,elapsedMins,elapsedSecs);
        checkEnough(mode1,elapsedHours);
        return str;
    }
    private void checkEnough(int mode,long elapsedHours){
        if(mode==1){
            if(age<=2){
            if(elapsedHours<11){
                enough=false;
            }
        }
        else if(age<=6){
            if(elapsedHours<8){
                enough=false;
            }
        }
        else if(age<=13){
            if(elapsedHours<9){
                enough=false;
            }
        }
        else if(age<=17){
            if(elapsedHours<8){
                enough=false;
            }
        }
        else if(age<=64){
            if(elapsedHours<6){
                enough=false;
            }
        }
        else{
            if(elapsedHours<5){
                enough=false;
            }
        }
        }
        else if(mode==3){
            if(elapsedHours<8.5){
                enough=false;
            }
        }
    }
    private void space(){
        System.out.println("Find your: \n1.Sleeping time\n2.Waking time\nOR\n3.Monitor sleep\nOR\n4.Set an alarm");
        mode2 = s.nextInt();
        if(mode2==1){
            wake();
        }
        else if(mode2==2){
            sleep();
        }
        else if(mode2==3){
            try{
            FileWriter fw = new FileWriter("history.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write("Sleeping in space\n");
            pw.close();
            
        }catch(Exception e){
            System.out.println("File cannot be found.");
        }
        monitor();
        }
        else if(mode2==4){
            alarm();
        }
    }
    private void alarm(){
        System.out.println("Set an alarm to wake you up(HH MM): ");
        hour = s.nextInt();
        minute = s.nextInt();
        LocalTime alarm = LocalTime.of(hour, minute);
        LocalTime offBlueLight = alarm.minusHours(11);
        if(LocalTime.now().equals(offBlueLight)){
            blueLightFilter=true;
        }
    }
}
