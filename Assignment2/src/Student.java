public class Student
{
    private String name;
    private int ID;
    
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
}
