package com.angela.project_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "quiz_submission")
public class QuizSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonBackReference(value = "quiz-quizSubmission")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "submission_time")
    private Date submissionTime;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    private String status;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public QuizSubmission(Quiz quiz, Student student, Date submissionTime, Integer durationMinutes, String status, Integer score) {
        this.quiz = quiz;
        this.student = student;
        this.submissionTime = submissionTime;
        this.durationMinutes = durationMinutes;
        this.status = status;
        this.score = score;
    }

    public QuizSubmission() {
    }
}
