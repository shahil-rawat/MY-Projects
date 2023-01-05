package location;

import java.util.Scanner;

public class DSide extends Location {

    public DSide(String name, Scanner in) {
        super(name, in);
    }

    @Override
    public boolean playGame() {

        greetings();
        

        System.out.println("Who inaugurated the B-Dome of BITS Goa?");
        System.out.println("(Answer with option number)");

        System.out.println("1. Pratibha Patil");
        System.out.println("2. Rahul Gandhi");
        System.out.println("3. Manmohan Singh");
        System.out.println("4. APJ Abdul Kalam");
        int ans = Integer.parseInt(in.nextLine());

        if(ans == 2) {
            System.out.println("That's right!");
        }
        else {
            System.out.println("Wrong answer, the right answer is option 2");
        }

        return ans == 2;
    }

    @Override
    protected String type() {
        return "Quiz";
    }
    
}