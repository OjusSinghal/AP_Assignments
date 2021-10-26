import java.util.Scanner;

public class SnakesNLadders {
    private final Floor[] floors;
    private final Dice dice;
    private Player player;
    private int score;
    
    public void setPlayer(Player player) { this.player = player; }
    
    public SnakesNLadders() {
        this.dice = new Dice(2);
        this.floors = new Floor[14];
        this.score = 0;
    }
    
    private void setDefaultFloors() {
        for (int i = 0; i < 14; i++)
            floors[i] = new Floor(i);
        floors[2] = new Elevator(2, 10);
        floors[5] = new Snake(5, 1);
        floors[8] = new Ladder(8, 12);
        floors[11] = new KingCobra(11,3 );
        floors[4] = new Dungeon(4);
        floors[6] = new Dungeon(6);
        floors[1] = new Dungeon(1);
    }
    
    public void runGame() {
        setDefaultFloors();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the player name and hit enter: ");
        setPlayer(new Player(scanner.nextLine()));
        System.out.println("The game setup is ready.");
        
        while (true) {
            System.out.print("____________________________________________\nHit enter to roll the dice.");
            String temp = scanner.nextLine();
            dice.roll();
            System.out.println("Dice gave " + dice.getFaceValue());
            System.out.flush();
            if (player.getPosition() == 0 && dice.getFaceValue() == 2) {
                System.out.println("Game cannot start until you get 1");
                continue;
            }
            if (player.getPosition() + dice.getFaceValue() > 13) {
                System.out.println("Player cannot move.");
                continue;
            }
            player.setPosition(Math.min(13, player.getPosition() + dice.getFaceValue()));
    
            Floor cur = floors[player.getPosition()];
            score += cur.getPoints();
            
            System.out.println("Player position: " + (player.getPosition()));
            System.out.println(player.getName() + " has reached " + cur.reachedStatement());
            System.out.println("Total points: " + score);
            
            if (!cur.isEmpty()) {
                player.setPosition(cur.getNextFloor());
                cur = floors[player.getPosition()];
                score += cur.getPoints();
                System.out.println("Player position: " + player.getPosition());
                System.out.println(player.getName() + " has reached " + cur.reachedStatement());
                System.out.println("Total points: " + score);
            }
            
            if (player.getPosition() == 13) {
                System.out.println("Game over.\n" + player.getName() + " accumulated " + score + " points.");
                return;
            }
            System.out.flush();
        
        }
    }
    
    public static void main(String[] args) {
        SnakesNLadders SnL = new SnakesNLadders();
        SnL.runGame();
    }
}