public class Student implements Commenter
{
    private final String name;
    private final int ID;
    
    public Student(String name, int ID) {
        this.ID = ID;
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public String toString() {
        return ID + ". " + name;
    }
}
