import java.util.ArrayList;

public class Quiz implements Assessable
{
    private final String question;
    private ArrayList<String> submissions;
    
    public Quiz(String question, int studentNumber)
    {
        this.question = question;
        this.submissions = new ArrayList<>(studentNumber);
    }
    
    
    public String toString() {
        return "Question: " + question;
    }
    
    
    public void takeSubmission(Student s)
    {
    
    }
}
