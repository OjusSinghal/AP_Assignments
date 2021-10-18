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
            instructors.add(new Instructor("Instructor-" + Integer.toString(i), i));
        for (int i = 0; i < 3; i++)
            students.add(new Student("Student-" + Integer.toString(i), i));
        
    
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
                int query = scanner.nextInt();
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
                            course.addLectureMaterials(new LectureSlide(content, topic));
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
                            course.addAssessables(new Assignment(problem, maxMarks));
                        }
                        break;
                    }
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    
                }
                
            }
        }
    }
    
    public static void main(String[] args) {
        BackPack backPack = new BackPack();
        backPack.runBackPack();
    }
}
