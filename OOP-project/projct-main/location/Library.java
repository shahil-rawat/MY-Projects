package location;

import java.util.Scanner;


public class Library extends Location {

    public Library(String name, Scanner in) {
        super(name, in);
    }
    @Override
    public boolean playGame() {

        greetings();
        

        System.out.println("Evaluate: 55 + 77 + 11 =");
        int ans = Integer.parseInt(in.nextLine());

        if(ans == 143) {
            System.out.println("That's right!");
        }
        else {
            System.out.println("Wrong answer, the right answer is 143");
        }

        return ans == 143;
    }

    @Override
    protected String type() {
        return "Math";
    }

}
