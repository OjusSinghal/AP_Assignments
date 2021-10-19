import java.time.LocalDateTime;
import java.util.ArrayList;

public class LectureSlide implements LectureMaterials
{
    private final ArrayList<String> content;
    private final String topic;
    private final int slideCount;
    private final LocalDateTime dateTime;
    private final Instructor instructor;
    
    public LectureSlide(ArrayList<String> content, String topic, Instructor instructor) {
        this.content = content;
        this.topic = topic;
        this.slideCount = content.size();
        this.dateTime = LocalDateTime.now();
        this.instructor = instructor;
    }
    
    public ArrayList<String> getContent() { return content; }
    
    public String getTopic() { return  topic; }
    
    public int getSlideCount() { return slideCount; }
    
    public String toString() {
        StringBuilder slideView = new StringBuilder("Title: " + topic + '\n');
        for (int i = 0; i < slideCount; i++) {
            slideView.append("Slide ").append(i + 1).append(content.get(i)).append("\n");
        }
        slideView.append("Number of slides: ").append(slideCount).append("\n");
        slideView.append("Date of upload: ").append(dateTime).append("\n");
        slideView.append("Uploaded by: ").append(instructor).append("\n");
        return slideView.toString();
    }
}
