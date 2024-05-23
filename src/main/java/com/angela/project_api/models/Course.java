package com.angela.project_api.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    @JsonBackReference(value = "course-instructor")
    private Instructor instructor;
    @Column(name = "enrollment_deadline")
    private Date enrollmentDeadline;
    @Column(name = "semester")
    private String semester;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Enrollment> enrollments;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Forum> forums;
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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Date getEnrollmentDeadline() {
        return enrollmentDeadline;
    }

    public void setEnrollmentDeadline(Date enrollmentDeadline) {
        this.enrollmentDeadline = enrollmentDeadline;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Course(String title, String description, Instructor instructor, Date enrollmentDeadline, String semester, List<Lesson> lessons, List<Enrollment> enrollments) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.enrollmentDeadline = enrollmentDeadline;
        this.semester = semester;
        this.lessons = lessons;
        this.enrollments = enrollments;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", instructor=" + instructor +
                ", enrollmentDeadline=" + enrollmentDeadline +
                ", semester='" + semester + '\'' +
                '}';
    }
}
