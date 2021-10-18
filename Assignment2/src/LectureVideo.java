public class LectureVideo implements LectureMaterials
{
    private String topic;
    private String fileName;
    private Instructor instructor;
    
    public LectureVideo(String topic, String fileName, Instructor instructor) {
        this.topic = topic;
        this.fileName = fileName;
        this.instructor = instructor;
    }
    
    public String getTopic() { return topic; }
    
    public String getFileName() { return fileName; }
}
