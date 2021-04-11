package com.reto.challenge.model;

public class Comment {

    private String author;
    private String message;
    private String timestamp;
    private Course courseComment;

    public Comment(String author, String message, String timestamp) {
        this.author = author;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Course getCourseComment() {
        return courseComment;
    }

    public void setCourseComment(Course courseComment) {
        this.courseComment = courseComment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}