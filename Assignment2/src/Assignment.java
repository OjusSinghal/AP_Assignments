public class Assignment implements Assessable
{
    private String problem;
    private int maxMarks;
    
    public Assignment(String problem, int maxMarks)
    {
        this.problem = problem;
        this.maxMarks = maxMarks;
    }
    
    public String getProblem()
    {
        return problem;
    }
    
    public int getMaxMarks()
    {
        return maxMarks;
    }
}
