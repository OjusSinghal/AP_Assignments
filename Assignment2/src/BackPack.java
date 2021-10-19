import java.util.ArrayList;
import java.util.Scanner;

public class BackPack
{
    private ArrayList<Instructor> instructors;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    
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
            addInstructor("Instructor-" + i, i);
        for (int i = 0; i < 3; i++)
            addStudent("Student-" + i, i);
        
    
        while(true) {
            System.out.println(login);
            int opt = scanner.nextInt();
            if (opt == 3) break;
            else if (opt == 1) {
                System.out.println("Instructors:");
                for (int i = 0; i < instructors.size(); i++)
                    System.out.println(Integer.toString(i) + " - " + instructors.get(i).getName());
                System.out.print("Choose id: ");
                Instructor instructor = instructors.get(scanner.nextInt());
                System.out.println("Welcome " + instructor.getName());
                System.out.println(instructorMenu);
                int query = 0;
                while (query != 9) {
                    query = scanner.nextInt();
                    switch (query) {
                        case 1: {
                            System.out.println("""
                                    1. Add lecture slide
                                    2. Add lecture video""");
                            int query2 = scanner.nextInt();
                            if (query2 == 1) {
                                System.out.println("Enter topic of slides: ");
                                String topic = scanner.nextLine();
                                System.out.println("Enter number of slides: ");
                                int slideCount = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter content of slides");
                                ArrayList<String> content = new ArrayList<>(slideCount);
                                for (int i = 0; i < slideCount; i++) {
                                    System.out.print("Content of slide " + Integer.toString(i) + ": ");
                                    content.set(i, scanner.nextLine());
                                }
                                course.addLectureMaterials(new LectureSlide(content, topic, instructor));
                            }
                            else if (query2 == 2) {
                                System.out.print("Enter topic of video: ");
                                String videoTopic = scanner.nextLine();
                                System.out.print("Enter filename of video: ");
                                String fileName = scanner.nextLine().trim();
                                if (!fileName.substring(fileName.length() - 4).equals(".mp4")) {
                                    System.out.println("Incorrect file extension. Try again.");
                                    continue;
                                }
                                course.addLectureMaterials(new LectureVideo(videoTopic, fileName, instructor));
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("""
                                    1. Add assignment
                                    2. Add Quiz""");
                            int query2 = scanner.nextInt();
                            scanner.nextLine();
                            if (query2 == 1) {
                                System.out.println("Enter problem statement: ");
                                String problem = scanner.nextLine();
                                System.out.println("Enter maximum marks: ");
                                int maxMarks = scanner.nextInt();
                                scanner.nextLine();
                                course.addAssessables(new Assignment(problem, maxMarks, students.size()));
                            }
                            else if (query2 == 2) {
                                System.out.println("Enter quiz question: ");
                                String question = scanner.nextLine();
                                course.addAssessables(new Quiz(question, students.size()));
                            }
                            break;
                        }
                        case 3: {
                            System.out.println(course.printLectureMaterials());
                            break;
                        }
                        case 4: {
                            System.out.println(course.printAssessables());
                            break;
                        }
                        case 5: {
                            ArrayList<Assessable> assessables = course.getAssessables();
                            if (assessables.size() == 0) {
                                System.out.println("No assessments posted yet. ");
                                break;
                            }
                            for (int i = 0; i < assessables.size(); i++)
                                System.out.println("ID: " + i + assessables.get(i).toString() + "\n_______________________________________");
                            System.out.print("Enter ID of assessment to view submissions: ");
                            Assessable assessable = assessables.get(scanner.nextInt());
                            scanner.nextLine();
                            System.out.println("Choose ID from these ungraded submissions:");
                            for (Student stu : assessable.getUngradedSubmissions())
                                System.out.println(stu);
                            Student stu = students.get(scanner.nextInt());
                            System.out.println(assessable.getSubmission(stu));
                            System.out.println("______________________________________");
                            System.out.println("Max marks: " + assessable.getMaxMarks());
                            System.out.print("Marks scored: ");
                            assessable.gradeSubmission(stu, instructor, scanner.nextInt());
                            scanner.nextLine();
                            break;
                        }
                        case 6: {
                            ArrayList<Assessable> open = course.getOpenAssessables();
                            if (open.size() == 0)
                                System.out.println("There are currently no open assessments.");
                            else {
                                for (int i = 0; i < open.size(); i++)
                                    System.out.println("ID: " + i + open.get(i).toString() + "\n________________________");
                                System.out.print("Enter ID of assessment to close: ");
                                open.get(scanner.nextInt()).close();
                                scanner.nextLine();
                            }
                            break;
                        }
                        case 7:
                        case 8: {
                            course.addComment(instructor);
                        }
                        case 9: {
                            break;
                        }
                    }
                }
                
            }
            else if (opt == 2) {
                System.out.println("Students:");
                for (int i = 0; i < students.size(); i++)
                    System.out.println(Integer.toString(i) + " - " + students.get(i).getName());
                System.out.print("Choose ID: ");
                Student student = students.get(scanner.nextInt());
                scanner.nextLine();
                int query = 0;
                while (query != 7) {
                    query = scanner.nextInt();
                    scanner.nextLine();
                    switch (query) {
                        case 1 : {
                            System.out.println(course.printLectureMaterials());
                            break;
                        }
                        case 2 : {
                            System.out.println(course.printAssessables());
                            break;
                        }
                        case 3 : {
                            ArrayList<Assessable> pending = course.getPendingAssessables(student);
                            for (int i = 0; i < pending.size(); i++)
                                System.out.println("ID: " + i + pending.get(i).toString());
                            System.out.print("Enter ID of assessment: ");
                            int assessmentID = scanner.nextInt();
                            scanner.nextLine();
                            pending.get(assessmentID).takeSubmission(student);
                        }
                        case 4 : {
                            System.out.println("Graded submissions");
                            course.printGradedAssessables(student);
                            System.out.println("Ungraded submissions");
                            course.printUngradedAssessables(student);
                            break;
                        }
                        case 5 : {
                        
                        }
                        case 6 : {
                            course.addComment(student);
                        }
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
