import java.util.ArrayList;
import java.util.Scanner;

public class Assignment implements Assessable
{
    private final String problem;
    private final int maxMarks;
    private ArrayList<String> submissions;
    
    public Assignment(String problem, int maxMarks, int studentNumber) {
        this.problem = problem;
        this.maxMarks = maxMarks;
        this.submissions = new ArrayList<>(studentNumber);
    }
    
    public String getProblem() { return problem; }
    
    public int getMaxMarks() { return maxMarks; }
    
    public String toString() {
        return "Assignment: " + problem + "  Max marks: " + Integer.toString(maxMarks);
    }

    public void takeSubmission(Student s) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename of assignment: ");
        String fileName = sc.nextLine().trim();
        if (!fileName.substring(fileName.length() - 4).equals(".zip"))
            System.out.println("Incorrect submission format, please submit in '.zip'");
        else {
            submissions.set(s.getID(), fileName);
        }
    }
}
