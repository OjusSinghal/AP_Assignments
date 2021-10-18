import java.util.ArrayList;

public class LectureSlide implements LectureMaterials
{
    private final ArrayList<String> content;
    private final String topic;
    private final int slideCount;
    
    public LectureSlide(ArrayList<String> content, String topic) {
        this.content = content;
        this.topic = topic;
        this.slideCount = content.size();
    }
    
    public ArrayList<String> getContent() { return content; }
    
    public String getTopic() { return  topic; }
    
    public int getSlideCount() { return slideCount; }
}
