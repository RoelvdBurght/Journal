package e.roel.journal;
import java.io.Serializable;

/* This class creates objects that store the data for the individual journal entrys */
public class JournalEntry implements Serializable {
    private int id;
    private String title;
    private String content;
    private String date;
    private String timestamp;
    private int mood;


    // Constructor
    public JournalEntry(String title, String content, String date, int mood) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.mood = mood;
    }

    // Getter and setter methods below
    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getMood() {
        return mood;
    }
}
