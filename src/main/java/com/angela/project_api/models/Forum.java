package com.angela.project_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "forum")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String category;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_activity")
    private Date lastActivity;
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "forum-forumThreads")
    private List<ForumThread> forumThreads;

    public List<ForumThread> getForumThreads() {
        return forumThreads;
    }

    public void setForumThreads(List<ForumThread> forumThreads) {
        this.forumThreads = forumThreads;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Forum(int id, String title, String description, String category, Course course, Date creationDate, Date lastActivity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.course = course;
        this.creationDate = creationDate;
        this.lastActivity = lastActivity;
    }

    public Forum() {
    }
}
