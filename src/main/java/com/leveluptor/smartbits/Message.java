package com.leveluptor.smartbits;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;

    private String summary;

    private String notes;

    private Timestamp timestamp;

    @ManyToOne
    private User author;

    Message() {

    }

    public List<Tag> getTags() {
        return tags;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "message_tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private List<Tag> tags;

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

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
