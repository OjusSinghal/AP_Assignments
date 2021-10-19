import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return content + " - " + name + '\n' + dateTime.format(form) + '\n';
    }
    
}
