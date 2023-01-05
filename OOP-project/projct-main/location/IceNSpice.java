package location;

import java.util.Scanner;

public class IceNSpice extends Location {

    public IceNSpice(String name, Scanner in) {
        super(name, in);
    }

    @Override
    public boolean playGame() {

        greetings();
        

        System.out.println("If you order 4 Oreo Coffees for 5 people, how much does each have to pay?");
        
        int ans = Integer.parseInt(in.nextLine());

        if(ans == 36) {
            System.out.println("That's right!");
        }
        else {
            System.out.println("Wrong answer, the right answer is 36");
        }

        return ans == 36;
    }

    @Override
    protected String type() {
        return "Math";
    }
    
}