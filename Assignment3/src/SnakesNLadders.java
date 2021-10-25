import java.util.Random;

public class SnakesNLadders
{
    private final Floor[] floors;
    private final Dice dice;
    
    public SnakesNLadders() {
        this.dice = new Dice(2);
        this.floors = new Floor[14];
    }
    
    public void runGame() {
    }
    
    public static void main(String[] args) {
        SnakesNLadders SnL = new SnakesNLadders();
        SnL.runGame();
    }
}


