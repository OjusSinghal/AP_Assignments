public class Instructor implements Commenter
{
    private String name;
    private int ID;
    
    public String getName()
    {
        return name;
    }
    
    public Instructor(String name, int ID) {
        this.ID = ID;
        this.name = name;
    }
}
