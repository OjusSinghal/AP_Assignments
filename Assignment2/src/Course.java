import java.util.ArrayList;
import java.util.Scanner;

public class Course
{
    private ArrayList<Instructor> instructors;
    private ArrayList<Student> students;
    private ArrayList<Assessable> assessables;
    private ArrayList<LectureMaterials> lectureMaterials;
    private ArrayList<Comment> comments;
    
    public Course() {
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.assessables = new ArrayList<>();
        this.lectureMaterials = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
    
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
    public void addLectureMaterials(LectureMaterials lectureMaterial) {
        lectureMaterials.add(lectureMaterial);
    }

    public void addAssessables(Assessable assessable) {
        assessables.add(assessable);
    }
    
    public ArrayList<Assessable> getAssessables() { return assessables; }
    
    public ArrayList<LectureMaterials> getLectureMaterials() { return lectureMaterials; }
    
    public String printLectureMaterials() {
        StringBuilder lm = new StringBuilder();
        for (LectureMaterials l : lectureMaterials)
            lm.append(l.toString());
        return lm.toString();
    }
    
    public String printAssessables() {
        StringBuilder pa = new StringBuilder();
        for (int i = 0; i < assessables.size(); i++)
            pa.append("ID: ").append(i).append(" ").append(assessables.get(i).toString()).append("\n____________________________\n");
        return pa.toString();
    }
    
    public ArrayList<Assessable> getPendingAssessables(Student student) {
        ArrayList<Assessable> pending = new ArrayList<>();
        for (Assessable assessable : assessables)
            if (!assessable.isSubmitted(student)) pending.add(assessable);
        return pending;
    }
    
    public ArrayList<Assessable> getOpenAssessables() {
        ArrayList<Assessable> open = new ArrayList<>();
        for (Assessable a : assessables)
            if (a.isOpen()) open.add(a);
        return open;
    }
    
    public void printGradedAssessables(Student student) {
        for (Assessable assessable : assessables)
            if (assessable.isGraded(student)) System.out.println(assessable.getSubmission(student));
    }
    
    public void printUngradedAssessables(Student student) {
        for (Assessable assessable : assessables)
            if (!assessable.isGraded(student)) System.out.println(assessable.getSubmission(student));
    }
    
    public void addComment(Commenter commenter) {
        System.out.println("Enter comment: ");
        Scanner sc = new Scanner(System.in);
        comments.add(new Comment(sc.nextLine(), commenter.getName()));
    }
}
