import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import location.*;
import location.Location;

public class Game {
	
	//Here we define variables related to the world and the player, for example
	//All the locations (which are a feature of the world)
	//Name and number of lives (feature of the player)

    private Set<Location> locations;
    //Creating a set for storing the locations
    
    Scanner in;

    private String name;

    private int lives;

    public Game(Scanner in) {
    	
    	//Constructor which takes an input stream
        this.in = in;
        locations = new HashSet<>();
        
        
    }

    public void setLives(int lives) {
    	//
        this.lives = lives;
    }

    public void addLocations() {
    	//To add locations
    	//We have defined classes for each one of the locations,
    	//Here we are just instantiating each of them and adding the object to our list of locations
        Location location = new BDome("B Dome", in);
        locations.add(location);
        location = new DSide("D Side", in);
        locations.add(location);
        location = new FoodKing("Food King", in);
        locations.add(location);
        location = new IceNSpice("Ice N Spice", in);
        locations.add(location);
        location = new Library("Library", in);
        locations.add(location);
        location = new Malakars("Malakars", in);
        locations.add(location);
    }
    
    private void checkWinMessage() {//Winning message display
    	if(locations.isEmpty()==true) {
    		System.out.println("");
    		System.out.println("");
    		System.out.println("Congratulations!!! You win the game!");
    	}
    }

    private void setPlayerName() {//Storing players name
        System.out.print("Enter name: ");
        name = in.nextLine();
        System.out.println("Welcome " + name + "!");
    }

    private Location getLocation(String loc) {//Receiving the locations name 
        for(Location location : locations) {
            if(location.getName().equalsIgnoreCase(loc))
                return location;
        }
        return null;
    }

    private void displayLocations() {//Displaying all the remaining locations in every turn
        for(Location location : locations) {
            System.out.println(location.getName());
        }

    }

    private void play() {
        while(locations.size() > 0) {//playing loop
            System.out.println("You have "+lives+" lives remaining");//displaying the remaining lives
            System.out.println("Select a location: ");
            displayLocations();
            String location = in.nextLine();

            Location selectedLocation = getLocation(location);

            if(selectedLocation == null) {//if some other string which is not in the hashset of location is selected
                System.out.println("Invalid location: " + location);
                System.out.println();
                continue;
            }

            boolean result = selectedLocation.playGame();

            if(result) {//if correct answer is given we remove that location

                locations.remove(selectedLocation);

            }
            else {
                if(--lives == 0) {//if no lives remain ---> game lost
                    System.out.println("You have lost the game, Thank you for playing!");
                    System.exit(0);
                }
                do {//otherwise ---> play a penalty game to resume choosing locations
                    System.out.println("Select a penalty game: ");
                    System.out.println("DICE | TIC-TAC-TOE");
                    String game = in.nextLine();
                    if(!(game.equalsIgnoreCase("DICE") || game.equalsIgnoreCase("TIC-TAC-TOE"))) {
                        System.out.println("INVALID GAME: " + game);
                        System.out.println();
                        continue;
                    }


                    if(game.equalsIgnoreCase("DICE")) {
                        // Dice game
                        boolean res = dice();
                        if(res)
                            break;
                    } else {
                        // TIC-TAC-TOE game
                        boolean res = tictactoe();
                        if(res)
                            break;
                    }

                } while(true);
            }

        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Game game = new Game(in);
        game.addLocations();
        game.setLives(3);//setting number of lives to 3
        game.checkWinMessage();
        game.setPlayerName();
        game.play();
    }

    private boolean dice() {
    	//Dice Game
    	// This is one of the two penalty games in the code.
    	// If the player gives a wrong answer at any location, he has the choice between playing two penalty games-
    	// Tic Tac Toe and the dice game.
    	//Player is allowed to exit the location only if he wins one of the penalty games.
        System.out.println("Dice");
        System.out.println();
        while(true) {
            System.out.println("Enter 1 to roll the dice. (You win if you get a 6)");
            in.nextLine();
            int rand = (int)(Math.random()*6) + 1;//we have used this method to generate a random integer from 1-6
            System.out.println("Dice: " + rand);
            if(rand == 6) {
                System.out.println("You pass by rolling a 6.");
                return true;
            }
            else {
                System.out.println("Sorry, you have lost the penalty game.. try again.");
                return false;
            }
        }
    }

    private boolean tictactoe() {
    	//Tic Tac Toe
    	// This is one of the two penalty games in the code.
    	// If the player gives a wrong answer at any location, he has the choice between playing two penalty games-
    	// Tic Tac Toe and the Dice Game.
    	//Player is allowed to exit the location only if he wins one of the penalty games.
    	
        System.out.println("TIC-TAC-TOE");
        System.out.println();
        
        // Scanner in = new Scanner(System.in);
        
        TicTakToe board = new TicTakToe();//Object
        String ai[]= new String[9];//This array is the set of moves the computer is going to make
        ai[0]="2 2";
        ai[1]="1 2";
        ai[2]="2 3";
        ai[3]="3 3";
        ai[4]="2 1";
        ai[5]="1 1";
        ai[6]="1 3";
        ai[7]="3 1";
        ai[8]="3 2";
        int counter = 0;
        do {

            System.out.println(board);
            char player = (counter%2 == 0) ? 'x' : 'o';//x is the user and o is the computer
            System.out.println("ENTER "+player+": ");
            System.out.println();
            int x=-1,y=-1;
            if(player=='x')
            {	
                x = in.nextInt()-1;
                y = in.nextInt()-1;
                boolean isValid = board.executePlay(x, y, player);
                if(!isValid) {
                    System.out.println("Invalid entry! Please enter a valid input");
                    System.out.println();
                    continue;
                }
            }
            else{
                for(int i=0;i<9;i++)
                {
                    x=(int)ai[i].charAt(0)-'0'-1;
                    y=(int)ai[i].charAt(2)-'0'-1;
                    if(board.executePlay(x,y,player)){
                        break;//choose the first location in the ai array and break after that
                    }
                }
            }
            counter++;

        } while(board.gameOver() == ' ');    
        //Checks after every move, whether the game is over or not
        //If the game is over then return the appropriate option
        	
        System.out.println(board);
        System.out.println("\n");
        char result = board.gameOver();
        if(result == 'd') {
            System.out.println("Draw");
            return false;
        }
        else if(result == 'x') {
            System.out.println("X wins!");
            return true;
        }
        else {
            System.out.println("O wins!");
            return false;
        }
    }

}