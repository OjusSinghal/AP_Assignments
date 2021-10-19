import java.util.ArrayList;
import java.util.Scanner;

public class Course
{
    private final ArrayList<Instructor> instructors;
    private final ArrayList<Student> students;
    private final ArrayList<Assessable> assessables;
    private final ArrayList<LectureMaterials> lectureMaterials;
    private final ArrayList<Comment> comments;
    
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
            lm.append(l.viewMaterial()).append('\n');
        if (lm.toString().equals("")) return "No Lecture materials currently added.";
        return lm.toString();
    }
    
    public String printAssessables() {
        if (assessables.size() == 0) return "No assessments currently added.";
        StringBuilder pa = new StringBuilder();
        for (int i = 0; i < assessables.size(); i++)
            pa.append("ID: ").append(i).append(" ").append(assessables.get(i).toString()).append("\n____________________________\n");
        return pa.toString();
    }
    
    public ArrayList<Assessable> getPendingAssessables(Student student) {
        ArrayList<Assessable> pending = new ArrayList<>();
        for (Assessable assessable : assessables)
            if (assessable.isOpen() && !assessable.isSubmitted(student)) pending.add(assessable);
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
            if (assessable.isSubmitted(student) &&
                    assessable.isGraded(student)) System.out.println(assessable.getSubmission(student));
    }
    
    public void printUngradedAssessables(Student student) {
        for (Assessable assessable : assessables)
            if (assessable.isSubmitted(student) &&
                    !assessable.isGraded(student)) System.out.println(assessable.getSubmission(student));
    }
    
    public void addComment(Commenter commenter) {
        System.out.print("Enter comment: ");
        Scanner sc = new Scanner(System.in);
        comments.add(new Comment(sc.nextLine(), commenter.getName()));
    }
    
    public void viewAllComments() {
        for (Comment c : comments) System.out.println(c);
    }
}
