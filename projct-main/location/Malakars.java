package location;

import java.util.Scanner;

public class Malakars extends Location {
    public Malakars(String name, Scanner in) {
        super(name, in);
    }

    @Override
    public boolean playGame() {

        greetings();
        

        System.out.println("Is it closer to CH1 or AH1?");
        System.out.println("1. AH1");
        System.out.println("2. CH1");
        System.out.println("(Enter option number)");
        int ans = Integer.parseInt(in.nextLine());

        if(ans == 1) {
            System.out.println("That's right!");
        }
        else {
            System.out.println("Wrong answer, the right answer is AH1");
        }

        return ans == 1;
    }

    @Override
    protected String type() {
        return "Quiz";
    }
}
