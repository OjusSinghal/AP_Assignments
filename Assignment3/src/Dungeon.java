import java.util.Random;
import java.util.Scanner;

public class Dungeon extends Floor
{
    private boolean reached;
    public Dungeon(int floorNumber) {
        super(floorNumber);
        this.reached = false;
    }
    
    @Override
    public String reachedStatement() { return (reached) ? "an Empty Floor" : "a Dungeon"; }
    @Override
    public boolean isEmpty() { return true; }
    
    @Override
    public int getPoints() {
        if (reached) return 1;
        reached = true;
        
        System.out.println("You have reached a Dungeon. \nIf you get 1, you get -5 points, \nif you get 2, you get +5 points bonus");
        System.out.println("Hit enter to roll the dice");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        Random r = new Random();
        return (r.nextInt(2) == 0) ? 5 : -5;
    }
    
}
