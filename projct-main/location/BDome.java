package location;

import java.util.Scanner;

public class BDome extends Location {

    public BDome(String name, Scanner in) {
        super(name, in);
    }

    @Override
    public boolean playGame() {

        greetings();
        

        System.out.println("How many diagonals are there in a 7 sided polygon?");
        int ans = Integer.parseInt(in.nextLine());

        if(ans == 14) {
            System.out.println("That's right!");
        }
        else {
            System.out.println("Wrong answer, the right answer is 14");
        }

        return ans == 14;
    }

    @Override
    protected String type() {
        return "Math";
    }
    
}
