import java.util.ArrayList;

public interface Assessable
{
    public void takeSubmission(Student s);
    public boolean isSubmitted(Student s);
    public boolean isOpen();
    public ArrayList<Student> getUngradedSubmissions();
    public String getSubmission(Student student);
    public int getMaxMarks();
    public void gradeSubmission(Student student, Instructor instructor, int marks);
    public boolean isGraded(Student student);
    public void close();
}