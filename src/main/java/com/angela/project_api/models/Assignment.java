package com.angela.project_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private Date deadLine;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonBackReference(value = "lesson-assignment")
    private Lesson lesson;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "assignment-assignmentSubmission")
    private List<AssignmentSubmission> assignmentSubmissions;

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

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadline(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<AssignmentSubmission> getAssignmentSubmissions() {
        return assignmentSubmissions;
    }

    public void setAssignmentSubmissions(List<AssignmentSubmission> assignmentSubmissions) {
        this.assignmentSubmissions = assignmentSubmissions;
    }

    public Assignment(String title, String description, Date deadLine, Lesson lesson, List<AssignmentSubmission> assignmentSubmissions) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.lesson = lesson;
        this.assignmentSubmissions = assignmentSubmissions;
    }

    public Assignment() {
    }
}
