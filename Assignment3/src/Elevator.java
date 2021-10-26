public class Elevator extends Floor
{
    public Elevator(int floorNumber, int nextFloor) {
        super(floorNumber, nextFloor);
        this.points = 4;
    }
    
    @Override
    public String reachedStatement() {
        return "an elevator";
    }
    
    @Override
    public boolean isEmpty() { return false; }
}
