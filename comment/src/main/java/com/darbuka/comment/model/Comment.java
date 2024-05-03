package com.darbuka.comment.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String authorId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    private String parentId;

    private int likesCount;

    private String status;

    @ElementCollection
    private List<String> tags;

    public Comment() {
    }

    public <E> Comment(String s, String user1, String s1, LocalDateTime now, Object o, int i, String active, ArrayList<E> es) {
    }

    public Comment(String commentId, String authorId, String content) {
        this.id = commentId;
        this.authorId = authorId;
        this.content = content;
    }

    public Comment(String authorId, String testComment) {
        this.authorId=authorId;
        this.content=testComment;
    }


    // getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}