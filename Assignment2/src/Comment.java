import java.time.LocalDateTime;

public class Comment
{
    private final String content;
    private final LocalDateTime dateTime;
    private final String name;
    
    public Comment(String content, String name) {
        this.content = content;
        this.name = name;
        this.dateTime = LocalDateTime.now();
    }
    
    public String toString() {
        return content + " - " + name + '\n' + dateTime.toString() + '\n';
    }
    
}
