public class Snake extends Floor
{
    public Snake(int floorNumber, int nextFloor) {
        super(floorNumber, nextFloor);
        this.points = -2;
    }
    
    @Override
    public String reachedStatement() {
        return "a snake floor";
    }
    
    @Override
    public boolean isEmpty() { return false; }
}
