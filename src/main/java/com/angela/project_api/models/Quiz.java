package com.angela.project_api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "time_limit_minutes")
    private Integer timeLimitMinutes;

    @Column(name = "attempts_allowed")
    private Integer attemptsAllowed;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonBackReference(value = "lesson-guiz")
    private Lesson lesson;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "quiz-quizSubmission")
    private List<QuizSubmission> quizSubmissions;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "quiz-question")
    private List<Question> questions;
    private Boolean visibility;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeLimitMinutes() {
        return timeLimitMinutes;
    }

    public void setTimeLimitMinutes(Integer timeLimitMinutes) {
        this.timeLimitMinutes = timeLimitMinutes;
    }

    public Integer getAttemptsAllowed() {
        return attemptsAllowed;
    }

    public void setAttemptsAllowed(Integer attemptsAllowed) {
        this.attemptsAllowed = attemptsAllowed;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<QuizSubmission> getQuizSubmissions() {
        return quizSubmissions;
    }

    public void setQuizSubmissions(List<QuizSubmission> quizSubmissions) {
        this.quizSubmissions = quizSubmissions;
    }

    public Quiz(String title, String description, Date dueDate, Integer timeLimitMinutes, Integer attemptsAllowed, Lesson lesson, List<QuizSubmission> quizSubmissions, List<Question> questions, Boolean visibility) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.timeLimitMinutes = timeLimitMinutes;
        this.attemptsAllowed = attemptsAllowed;
        this.lesson = lesson;
        this.quizSubmissions = quizSubmissions;
        this.questions = questions;
        this.visibility = visibility;
    }

    public Quiz() {
    }
}
