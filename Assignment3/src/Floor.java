public class Floor
{
    protected final int floorNumber;
    protected final int nextFloor;
    protected int points;
    
    
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.nextFloor = floorNumber + 1;
        this.points = 1;
    }
    
    protected Floor(int floorNumber, int nextFloor) {
        this.floorNumber = floorNumber;
        this.nextFloor = nextFloor;
    }
    
    public String reachedStatement() {
        return "an Empty Floor";
    }
    
    public boolean isEmpty() { return true; }
    
    public int getNextFloor() { return nextFloor; }
    public int getPoints() { return points; }
}
