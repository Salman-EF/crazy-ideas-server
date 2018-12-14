package com.crazyideas.models;

import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("thinkers")
public class Thinker {

    @Id
    private ObjectId id;
    private String name;

    public Thinker() {}
    public Thinker(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Thinker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
