package com.application.blogger.logic;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "post")
public class Post implements Serializable {
    @DatabaseField(generatedId = true)
    public Long id;
    @DatabaseField(dataType = DataType.STRING, index = true)
    public String title;
    @DatabaseField(dataType = DataType.STRING)
    public String date;
    @DatabaseField(dataType = DataType.STRING)
    public String message;
    @DatabaseField(dataType = DataType.STRING)
    public String author;

    public Post() {
    }

    public Post(String title, String date, String message, String author) {
        this.title = title;
        this.date = date;
        this.message = message;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
