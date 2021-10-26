public class Ladder extends Floor
{
    public Ladder(int floorNumber, int nextFloor) {
        super(floorNumber, nextFloor);
        this.points = 2;
    }
    
    @Override
    public String reachedStatement() {
        return "a ladder";
    }
    
    @Override
    public boolean isEmpty() { return false; }
}
