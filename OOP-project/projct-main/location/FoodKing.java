package location;

import java.util.*;

public class FoodKing  extends Location{

    public FoodKing(String name, Scanner in) {
        super(name, in);
    }

    @Override
    public boolean playGame() {

        greetings();
        

        System.out.println("What is 5! ?");
        int ans = Integer.parseInt(in.nextLine());

        if(ans == 120) {
            System.out.println("That's right!");
        }
        else {
            System.out.println("Wrong answer, the right answer is 120");
        }

        return ans == 120;
    }

    @Override
    protected String type() {
        return "Math";
    }
    
}
