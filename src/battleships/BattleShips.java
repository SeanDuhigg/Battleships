package battleships;
import java.util.*;
import java.math.*;

public class BattleShips {

    private static String[][] myMap = new String[10][10];
    private static String[][] computerMisses = new String[10][10];
    public static void main(String[] args) {
        
        printMap();
        int x = 0;
        int y = 0;
        int myCount = 0;
        int compCount = 0;
        Scanner input = new Scanner(System.in); //This line creates a Scanner for you to use
        
        //User deploys their ships
        for (int z=0; z<5; z++){
            
                System.out.print("Enter X coordinate for your ship:(0-9) ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship:(0-9) ");
                y = input.nextInt();
            while ((x<0||x>9)||(y<0||y>9)){
                System.out.println("Out of bounds, try again");
                System.out.print("Enter X coordinate for your ship:(0-9) ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship:(0-9) ");
                y = input.nextInt();
            }
                
            while(myMap[x][y] == "1"){
                System.out.println("Try again, ship already there...");
                System.out.print("Enter X coordinate for your ship:(0-9) ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship:(0-9) ");
                y = input.nextInt();
            }
            
            myMap[x][y] = "1";
        }    
        printMap();
        
        int randX,randY;
        //Computer deploys their ships
        System.out.println("Computer is deploying ships");
        for (int z=0; z<5; z++){
                
                Random rand = new Random();
                randX = rand.nextInt(10);
                randY = rand.nextInt(10);
                
                
            while(myMap[randX][randY] == "1" || myMap[randX][randY] == "2"){
                
                randX = rand.nextInt(10);
                randY = rand.nextInt(10);
            }
            
            myMap[randX][randY] = "2";
            System.out.println("ship DEPLOYED");
        }    
        printMap();
        
        while(myCount < 5 && compCount < 5){
            System.out.println("YOUR TURN");
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();
            while ((x<0||x>9)||(y<0||y>9)){
                System.out.println("Out of bounds, try again");
                System.out.print("Enter X coordinate for your ship:(0-9) ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship:(0-9) ");
                y = input.nextInt();
            }
            if(myMap[x][y] == "2"){
            
               System.out.println("Boom! You sunk the ship!");
               myMap[x][y] = "!";
               printMap();
               myCount++;
            
            }else if(myMap[x][y] == "1"){
            
                System.out.println("Oh no, you sunk your own ship :(");
                myMap[x][y] = "x";
                printMap();
                compCount++;
            
            }else{
            
                System.out.println("You missed");
                if(myMap[x][y] != "x" || myMap[x][y] != "!")
                    myMap[x][y] = "-";
                printMap();
            }
       
            System.out.println("COMPUTER'S TURN");
            Random rand = new Random();
            randX = rand.nextInt(10);
            randY = rand.nextInt(10);
            while(myMap[randX][randY] == "!" || computerMisses[randX][randY] == "miss"){//if the computer selects an already sunk ship or a spot already checked, reselect
                
                randX = rand.nextInt(10);
                randY = rand.nextInt(10);
                
            }
            if(myMap[randX][randY] == "1"){
            
                System.out.println("The Computer sunk one of your ships!");
                myMap[randX][randY] = "x";
                printMap();
                compCount++;
            
            }else if(myMap[randX][randY] == "2"){
            
                System.out.println("The Computer sunk one of its own ships");
                myMap[randX][randY] = "!";
                printMap();
                myCount++;
                      
            }else{
            
                System.out.println("Computer missed");
                computerMisses[randX][randY] = "miss";
                printMap();
            }
            
            
            
        }
        if (compCount == 5){
            System.out.println("Sorry, the computer wins :(");
            System.out.printf("Your ships: %d | Computer Ships: %d", 5-myCount, 5-compCount);
            System.out.println();
        }else if (myCount == 5){
            System.out.println("Hooray! You win the battle :)");
            System.out.printf("Your ships: %d | Computer Ships: %d", 5-myCount, 5-compCount);
            System.out.println();
        }
    }
    
    public static void printMap(){
        System.out.println("  0123456789");
        for(int x = 0; x < myMap.length; x++){
            System.out.print(x + "|");
            for(int y = 0; y < myMap.length; y++){
                if(myMap[x][y] == null){
                    System.out.print(" ");
                }else{
                    if(myMap[x][y]=="1")
                        System.out.print("@");
                    else if(myMap[x][y] == "2")
                        System.out.print(" ");
                    else if(myMap[x][y] == "-")
                        System.out.print("-");
                    else if(myMap[x][y] == "x")
                        System.out.print("x");
                    else if(myMap[x][y] == "!")
                        System.out.print("!");
                    else if(myMap[x][y] == "miss")
                        System.out.print(" ");
                }
            }
            System.out.println("|"+x);
        }
        System.out.println("  0123456789");
        
    }
    
}
