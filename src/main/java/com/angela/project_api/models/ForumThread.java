package com.angela.project_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "forum_thread")
public class ForumThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    @JsonBackReference(value = "forum-forumThreads")
    private Forum forum;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String title;
    private String category;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_activity")
    private Date lastActivity;
    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "forumThread-forumPost")
    private List<ForumPost> forumPosts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public ForumThread(int id, Forum forum, Student student, String title, String category, Date creationDate, Date lastActivity) {
        this.id = id;
        this.forum = forum;
        this.student = student;
        this.title = title;
        this.category = category;
        this.creationDate = creationDate;
        this.lastActivity = lastActivity;
    }

    public ForumThread() {
    }
}
