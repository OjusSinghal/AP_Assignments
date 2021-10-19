import java.util.ArrayList;
import java.util.Scanner;

public class BackPack
{
    private final ArrayList<Instructor> instructors;
    private final ArrayList<Student> students;
    private final ArrayList<Course> courses;
    
    public BackPack() {
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
    
    public void runBackPack() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Backpack");
        String login = """
                1. Enter as instructor
                2. Enter as student
                3. Exit""";
    
        String instructorMenu = """
                _______________________________
                INSTRUCTOR MENU
                1. Add class material
                2. Add assessments
                3. View lecture materials
                4. View assessments
                5. Grade assessments
                6. Close assessment
                7. View comments
                8. Add comments
                9. Logout""";
    
        String studentMenu = """
                _________________________________
                STUDENT MENU
                1. View lecture materials
                2. View assessments
                3. Submit assessment
                4. View grades
                5. View comments
                6. Add comments
                7. Logout""";
        
        
        courses.add(new Course());
        Course course = courses.get(0);
        for (int i = 0; i < 2; i++)
        {
            addInstructor("Instructor-" + i, i);
            course.addInstructor(new Instructor("Instructor-" + i, i));
        }
        for (int i = 0; i < 3; i++) {
            course.addStudent(new Student("Student-" + i, i));
            addStudent("Student-" + i, i);
        }
        
    
        while(true) {
            System.out.println(login);
            int opt = scanner.nextInt();
            if (opt == 3) break;
            else if (opt == 1) {
                System.out.println("Instructors:");
                for (int i = 0; i < instructors.size(); i++)
                    System.out.println(i + " - " + instructors.get(i).getName());
                System.out.print("Choose id: ");
                Instructor instructor = instructors.get(scanner.nextInt());
                System.out.println("Welcome " + instructor.getName());
                while (true) {
                    System.out.println(instructorMenu);
                    int query = scanner.nextInt();
                    if (query == 9) break;
                    switch (query) {
                        case 1 -> {
                            System.out.println("""
                                    1. Add lecture slide
                                    2. Add lecture video""");
                            int query2 = scanner.nextInt();
                            scanner.nextLine();
                            if (query2 == 1) {
                                System.out.print("Enter topic of slides: ");
                                String topic = scanner.nextLine();
                                System.out.print("Enter number of slides: ");
                                int slideCount = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter content of slides");
                                ArrayList<String> content = new ArrayList<>();
                                for (int i = 0; i < slideCount; i++) {
                                    System.out.print("Content of slide " + (i + 1) + ": ");
                                    content.add(scanner.nextLine());
                                }
                                course.addLectureMaterials(new LectureSlide(content, topic, instructor));
                            }
                            else if (query2 == 2) {
                                System.out.print("Enter topic of video: ");
                                String videoTopic = scanner.nextLine();
                                System.out.print("Enter filename of video: ");
                                String fileName = scanner.nextLine().trim();
                                if (!fileName.endsWith(".mp4")) {
                                    System.out.println("Incorrect file extension. Try again.");
                                    continue;
                                }
                                course.addLectureMaterials(new LectureVideo(videoTopic, fileName, instructor));
                            }
                        }
                        case 2 -> {
                            System.out.println("""
                                    1. Add assignment
                                    2. Add Quiz""");
                            int query2 = scanner.nextInt();
                            scanner.nextLine();
                            if (query2 == 1) {
                                System.out.print("Enter problem statement: ");
                                String problem = scanner.nextLine();
                                System.out.print("Enter maximum marks: ");
                                int maxMarks = scanner.nextInt();
                                scanner.nextLine();
                                course.addAssessables(new Assignment(problem, maxMarks, students.size()));
                            }
                            else if (query2 == 2) {
                                System.out.print("Enter quiz question: ");
                                String question = scanner.nextLine();
                                course.addAssessables(new Quiz(question, students.size()));
                            }
                        }
                        case 3 -> System.out.println(course.printLectureMaterials());
                        case 4 -> System.out.println(course.printAssessables());
                        case 5 -> {
                            ArrayList<Assessable> assessables = course.getAssessables();
                            if (assessables.size() == 0) {
                                System.out.println("No assessments posted yet. ");
                                break;
                            }
                            for (int i = 0; i < assessables.size(); i++)
                                System.out.println("ID: " + i + " " + assessables.get(i).toString() + "\n_______________________________________");
                            System.out.print("Enter ID of assessment to view submissions: ");
                            Assessable assessable = assessables.get(scanner.nextInt());
                            
                            scanner.nextLine();
                            ArrayList<Student> ungraded = assessable.getUngradedSubmissions();
                            if (ungraded.size() == 0) {
                                System.out.println("No submissions yet for this assessment");
                                break;
                            }
                            System.out.println("Choose ID from these ungraded submissions:");
                            for (Student stu : ungraded)
                                System.out.println(stu);
                            Student stu = students.get(scanner.nextInt());
                            System.out.println(assessable.getSubmission(stu));
                            System.out.println("______________________________________");
                            System.out.println("Max marks: " + assessable.getMaxMarks());
                            System.out.print("Marks scored: ");
                            assessable.gradeSubmission(stu, instructor, scanner.nextInt());
                            scanner.nextLine();
                        }
                        case 6 -> {
                            ArrayList<Assessable> open = course.getOpenAssessables();
                            if (open.size() == 0)
                                System.out.println("There are currently no open assessments.");
                            else {
                                for (int i = 0; i < open.size(); i++)
                                    System.out.println("ID: " + i + " " + open.get(i).toString() + "\n________________________");
                                System.out.print("Enter ID of assessment to close: ");
                                open.get(scanner.nextInt()).close();
                                scanner.nextLine();
                            }
                        }
                        case 7 -> course.viewAllComments();
                        case 8 -> course.addComment(instructor);
                    }
                }
            }
            else if (opt == 2) {
                System.out.println("Students:");
                for (int i = 0; i < students.size(); i++)
                    System.out.println(i + " - " + students.get(i).getName());
                System.out.print("Choose ID: ");
                Student student = students.get(scanner.nextInt());
                scanner.nextLine();
                System.out.println("Welcome student " + student.getID());
                while (true) {
                    System.out.println(studentMenu);
                    int query = scanner.nextInt();
                    scanner.nextLine();
                    if (query == 7) break;
                    switch (query) {
                        case 1 -> System.out.println(course.printLectureMaterials());
                        case 2 -> System.out.println(course.printAssessables());
                        case 3 -> {
                            ArrayList<Assessable> pending = course.getPendingAssessables(student);
                            if (pending.size() == 0) {
                                System.out.println("No pending assessments.");
                                continue;
                            }
                            for (int i = 0; i < pending.size(); i++)
                                System.out.println("ID: " + i + "  " + pending.get(i).toString());
                            System.out.print("Enter ID of assessment: ");
                            int assessmentID = scanner.nextInt();
                            scanner.nextLine();
                            pending.get(assessmentID).takeSubmission(student);
                        }
                        case 4 -> {
                            System.out.println("Graded submissions");
                            course.printGradedAssessables(student);
                            System.out.println("Ungraded submissions");
                            course.printUngradedAssessables(student);
                        }
                        case 5 -> course.viewAllComments();
                        case 6 -> course.addComment(student);
                    }
                }
                
            }
        }
    }
    
    public void addInstructor(String name, int ID) {
        instructors.add(new Instructor(name, ID));
    }
    
    public void addStudent(String name, int ID) {
        students.add(new Student(name, ID));
    }
    
    public static void main(String[] args) {
        BackPack backPack = new BackPack();
        backPack.runBackPack();
    }
}
