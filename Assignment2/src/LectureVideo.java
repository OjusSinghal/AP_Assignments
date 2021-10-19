import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LectureVideo implements LectureMaterials
{
    private final String topic;
    private final String fileName;
    private final Instructor instructor;
    private final LocalDateTime dateTime;
    
    public LectureVideo(String topic, String fileName, Instructor instructor) {
        this.topic = topic;
        this.fileName = fileName;
        this.instructor = instructor;
        this.dateTime = LocalDateTime.now();
    }
    
    public String getTopic() { return topic; }
    
    public String getFileName() { return fileName; }
    
    public String viewMaterial() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "\nTitle of video: " + topic +
                "\nVideo file: " + fileName +
                "\nDate of upload: " + dateTime.format(form) +
                "\nUploaded by: " + instructor.getName();
    }
}
