package com.leveluptor.smartbits;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;

    private String summary;

    private String notes;

    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn
    private User author;

    Message() {

    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Message(String summary, String notes, User author) {
        this.summary = summary;
        this.notes = notes;
        this.author = author;
        //todo java 8?
        timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", notes='" + notes + '\'' +
                ", timestamp=" + timestamp +
                ", author=" + author +
                '}';
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
