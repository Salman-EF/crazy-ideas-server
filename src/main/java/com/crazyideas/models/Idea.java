package com.crazyideas.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Document("ideas")
public class Idea {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private Date created_at;
    private Thinker thinker;

    public Idea() {}
    public Idea(String title) {
        this.title = title;
    }
    public Idea(String title, String content, Date created_at, Thinker thinker) {
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.thinker = thinker;
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) {this.id = id;}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getCreated_at() { return created_at; }
    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Thinker getThinker() { return thinker; }
    public void setThinker(Thinker thinker) { this.thinker = thinker; }

    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", thinker=" + thinker +
                '}';
    }
}
