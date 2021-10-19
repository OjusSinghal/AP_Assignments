import java.util.ArrayList;
import java.util.Scanner;

public class Assignment implements Assessable
{
    private class Submission {
        private final Student student;
        private Instructor instructor;
        private final String fileName;
        private int marks;
    
        public Submission(Student student, String fileName) {
            this.student = student;
            this.fileName = fileName;
            this.marks = -1;
            instructor = null;
        }
        
        public void grade(Instructor instructor, int marks) { this.marks = marks; this.instructor = instructor; }
        public int getMarks() { return marks; }
        public Student getStudent() { return student; }
        public String getFileName() { return fileName; }
        
        public String toString() {
            if (marks == -1) return "Submission: " + fileName;
            return "Submission: " + fileName + "\nMarks scored: " + marks + "\nGraded by: " + instructor.getName();
        }
    }
    
    private final String problem;
    private final int maxMarks;
    private Submission[] submissions;
    private boolean open;
    
    
    public Assignment(String problem, int maxMarks, int studentNumber) {
        this.problem = problem;
        this.maxMarks = maxMarks;
        this.submissions = new Submission[studentNumber];
        this.open = true;
    }
    
    public String getProblem() { return problem; }
    
    public int getMaxMarks() { return maxMarks; }
    
    @Override
    public void gradeSubmission(Student student, Instructor instructor, int marks) {
        submissions[student.getID()].grade(instructor, marks);
    }
    
    @Override
    public boolean isGraded(Student student) {
        if (submissions[student.getID()] != null)
            return submissions[student.getID()].getMarks() >= 0;
        return false;
    }
    
    public String toString() {
        return "Assignment: " + problem + "  Max marks: " + Integer.toString(maxMarks);
    }

    public void takeSubmission(Student s) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename of assignment: ");
        String fileName = sc.nextLine().trim();
        if (!fileName.substring(fileName.length() - 4).equals(".zip"))
            System.out.println("Incorrect submission format, please submit in '.zip'");
        else submissions[s.getID()] = new Submission(s, fileName);
    }
    
    @Override
    public boolean isSubmitted(Student s)
    {
        return (submissions[s.getID()] != null);
    }
    
    @Override
    public boolean isOpen() { return open; }
    
    @Override
    public ArrayList<Student> getUngradedSubmissions()
    {
        ArrayList<Student> ungraded = new ArrayList<>();
        for (Submission sub : submissions)
            if (sub != null && sub.getMarks() == -1) ungraded.add(sub.getStudent());
        return ungraded;
    }
    
    @Override
    public String getSubmission(Student student){
        return submissions[student.getID()].toString();
    }
    
    public void close() { open = false; }
}
