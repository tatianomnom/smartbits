package com.leveluptor.smartbits;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    protected Message() {

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


    public Message(String summary, String notes) {
        this.summary = summary;
        this.notes = notes;
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
                '}';
    }
}
