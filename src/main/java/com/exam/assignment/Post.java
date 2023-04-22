package com.exam.assignment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Post {
    private int id;
    private String creatorName;
    private String creatorLastName;
    private String postContent;
    private LocalDateTime postCreationDate;


    private Post(int id, String creatorName, String creatorLastName, String postContent, LocalDateTime postCreationDate) {
        this.id = id;
        this.creatorName = creatorName;
        this.creatorLastName = creatorLastName;
        this.postContent = postContent;
        this.postCreationDate = postCreationDate;
    }
    private Post(String creatorName, String creatorLastName, String postContent, LocalDateTime postCreationDate) {
        this.creatorName = creatorName;
        this.creatorLastName = creatorLastName;
        this.postContent = postContent;
        this.postCreationDate = postCreationDate;
    }

    public static Post of(int id, String creatorName, String creatorLastName, String postContent, LocalDateTime postCreationDate){
        return new Post(id, creatorName, creatorLastName, postContent, postCreationDate);
    }


    public static Post of(String creatorName, String creatorLastName, String postContent){
        return new Post(creatorName, creatorLastName, postContent, null);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorLastName() {
        return creatorLastName;
    }

    public void setCreatorLastName(String creatorLastName) {
        this.creatorLastName = creatorLastName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public LocalDateTime getPostCreationDate() {
        return postCreationDate;
    }

    public void setPostCreationDate(LocalDateTime postCreationDate) {
        this.postCreationDate = postCreationDate;
    }
}
