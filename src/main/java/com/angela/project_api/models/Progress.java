package com.angela.project_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "enrollment_id", unique = true)
    @JsonIgnore
    private Enrollment enrollment;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "completed_lessons",
            joinColumns = @JoinColumn(name = "progress_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lesson> completedLessons;

    @ManyToOne(optional = true)
    @JoinColumn(name = "current_lesson_id")
    private Lesson currentLesson;

    @Column(name = "last_access")
    private Date lastAccess;

    @Column(name = "overall_progress")
    private float overallProgress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public List<Lesson> getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(List<Lesson> completedLessons) {
        this.completedLessons = completedLessons;
    }

    public Lesson getCurrentLesson() {
        return currentLesson;
    }

    public void setCurrentLesson(Lesson currentLesson) {
        this.currentLesson = currentLesson;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public float getOverallProgress() {
        return overallProgress;
    }

    public void setOverallProgress(float overallProgress) {
        this.overallProgress = overallProgress;
    }

    public Progress(int id, Enrollment enrollment, List<Lesson> completedLessons, Lesson currentLesson, Date lastAccess, float overallProgress) {
        this.id = id;
        this.enrollment = enrollment;
        this.completedLessons = completedLessons;
        this.currentLesson = currentLesson;
        this.lastAccess = lastAccess;
        this.overallProgress = overallProgress;
    }

    public Progress() {
    }
}
