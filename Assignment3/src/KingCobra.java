public class KingCobra extends Floor
{
    public KingCobra(int floorNumber, int nextFloor) {
        super(floorNumber, nextFloor);
        this.points = -4;
    }
    
    @Override
    public String reachedStatement() {
        return "a King Cobra floor";
    }
    
    @Override
    public boolean isEmpty() { return false; }
}
