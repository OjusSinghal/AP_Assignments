import java.util.ArrayList;
import java.util.Scanner;

public class Quiz implements Assessable
{
    
    private class Submission {
        private final Student student;
        private Instructor instructor;
        private final String answer;
        private int marks;
        
        public Submission(Student student, String answer) {
            this.student = student;
            this.answer = answer;
            this.marks = -1;
            instructor = null;
        }
        
        public void grade(int marks, Instructor instructor) { this.marks = marks; this.instructor = instructor; }
        public int getMarks() { return marks; }
        public Student getStudent() { return student; }
        public String getAnswer() { return answer; }
        public String toString() {
            if (marks == -1) return "Submission: " + answer;
            return "Submission: " + answer + "\nMarks scored: " + marks + "\nGraded by: " + instructor;
        }
    }
    
    private final String question;
    private Submission[] submissions;
    private boolean open;
    
    public Quiz(String question, int studentNumber)
    {
        this.question = question;
        this.submissions = new Submission[studentNumber];
        this.open = true;
    }
    
    
    public String toString() {
        return "Question: " + question;
    }
    
    public void takeSubmission(Student s)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print(question);
        submissions[s.getID()] = new Submission(s, sc.nextLine());
    }
    
    @Override
    public boolean isSubmitted(Student s)
    {
        return submissions[s.getID()].getAnswer() != null;
    }
    
    public boolean isOpen() { return open; }
    
    @Override
    public ArrayList<Student> getUngradedSubmissions()
    {
        ArrayList<Student> ungraded = new ArrayList<>();
        for (Submission sub : submissions)
            if (sub != null && sub.getMarks() == -1) ungraded.add(sub.getStudent());
        return ungraded;
    }
    
    public String getSubmission(Student student){
        return submissions[student.getID()].toString();
    }
    
    @Override
    public int getMaxMarks() { return 1; }
    
    @Override
    public void gradeSubmission(Student student, Instructor instructor, int marks) {
        submissions[student.getID()].grade(marks, instructor);
    }
    
    public boolean isGraded(Student student) {
        return submissions[student.getID()].getMarks() >= 0;
    }
    
    public void close() { open = false; }
}
