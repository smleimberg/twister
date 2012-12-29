/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twister;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author smleimberg
 */
public class Twister {

    public static void main(String[] args) {
        //Game vars:
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        String[] limbs = {"LH","LF","RF","RH"};
        String[] colors = {"G","Y","B","R"};
        String[] names = new String[4];
        String[][] playerState = new String[4][4];
        boolean[] playing = {true,true,true,true};
        int playerCount = 0;
        int firstOut = 0;
        int longestName = 0;
        int longestInstruction = 18;
        int roundCount = 0;
        
        //Game Setup Questinos:
        System.out.println("\nWelcome to the Java Twister Spinner!");
        System.out.println("How many people are playing Twister?");
        while(playerCount != 2 && playerCount != 3 && playerCount != 4 ){
            System.out.print("Choose 2, 3 or 4: ");
            playerCount = in.nextInt();
        }
        System.out.println("1=First Out Loses. 2=Last Person Standing Wins.");
        while(firstOut!=1 && firstOut!=2){
            System.out.print("Choose 1 or 2: ");
            firstOut = in.nextInt();
        }
        for(int i=0; i<playerCount; i++){
            System.out.print("What is the first name of player "+(i+1)+": ");
            names[i]= in.next();
            if(names[i].length()>longestName){
                longestName=names[i].length();
            }
        }
        System.out.println("Examples:");
        System.out.println("RHR means Right Hand Red");
        System.out.println("RFY means Right Foot Yellow");
        System.out.println("LFB means Left Foot Blue");
        System.out.println("LHG means Left Hand Green");
        System.out.println("Game Start!");
        
        
        //Game Loop:
        while(true){
            for(int i=0; i<playerCount; i++){
                if(playing[i]){
                    int limb = rand.nextInt(4);
                    int color = rand.nextInt(4);
                    playerState[i][limb] = limbs[limb]+colors[color];
                    System.out.print(spaces(names[i],longestName)+": "+playerState[i][limb]+". ");
                    System.out.print(spaces("Did "+names[i]+" succeed?",longestName+13)+" [y/n]:");
                    if(!yes(in.next())){
                        playing[i]=false;
                        System.out.println(names[i]+" is out!");
                        if(firstOut==1){
                            System.out.println("\nGAME OVER!");
                            System.out.println(winner(false,playerCount,playing,names)+" lost!");
                            System.out.println("You played "+roundCount+" complete rounds.");
                            System.exit(0);
                        }else{
                            if(lastMan(playerCount,playing)){
                                System.out.println("\nGAME OVER!");
                                System.out.println(winner(true,playerCount,playing,names)+" wins!");
                                System.out.println("You played "+roundCount+" complete rounds.");
                                System.exit(0);
                            }
                        }
                    }else{
                        System.out.print(spaces(names[i],longestName)+": ");
                        for(int j=0;j<4;j++){
                            if(playerState[i][j]==null || playerState[i][j].isEmpty()){
                                System.out.print("  \t");
                            }else{
                                System.out.print(playerState[i][j]+"\t");
                            }
                        }
                        System.out.println("\n");
                    }
                }
            }
            roundCount++;
            System.out.println("Round "+roundCount+" complete!");
        }
    }
    
    static String winner(boolean win, int playerCount,boolean[] playing, String[] names){
        for(int i=0;i<playerCount;i++){
            if(playing[i]==win){
                return names[i];
            }
        }
        return "";
    }
    
    static boolean lastMan(int playerCount,boolean[] playing){
        int count = 0;
        for(int i=0;i<playerCount;i++){
            if(playing[i]){
                count++;
            }
        }
        if(count==1){
            return true;
        }
        return false;
    }
    
    static boolean yes(String in){
        if(in.toLowerCase().equals("y") || in.toLowerCase().equals("yes")){
            return true;
        }
        return false;
    }
    
    static String spaces(String in, int max){
        int count = 0;
        count = max-in.length();
        String spaces = in;
        for(int i=0;i<count;i++){
            spaces+=" ";
        }
        return spaces;
    }
    
    
}
