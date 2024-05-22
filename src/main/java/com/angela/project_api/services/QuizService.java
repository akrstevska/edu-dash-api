package com.angela.project_api.services;

import com.angela.project_api.models.Quiz;
import com.angela.project_api.models.Lesson;
import com.angela.project_api.repositories.QuizRepository;
import com.angela.project_api.repositories.LessonRepository;
import com.angela.project_api.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private LessonRepository lessonRepository;
    
    public boolean createQuiz(Quiz quiz) {
        Lesson lesson = lessonRepository.findById(quiz.getLesson().getId()).orElse(null);

        if (lesson != null) {
            quiz.setLesson(lesson);
            quizRepository.save(quiz);
            return true;
        }
        return false;
    }


    public boolean deleteQuiz(int id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        if (quiz != null) {
            quizRepository.delete(quiz);
            return true;
        }
        return false;
    }

    public boolean updateQuiz(int quizId, Quiz updatedQuiz) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            if (updatedQuiz.getTitle() != null) {
                quiz.setTitle(updatedQuiz.getTitle());
            }
            if (updatedQuiz.getDescription() != null) {
                quiz.setDescription(updatedQuiz.getDescription());
            }
            if (updatedQuiz.getLesson() != null) {
                quiz.setLesson(updatedQuiz.getLesson());
            }
            if (updatedQuiz.getDueDate() != null) {
                quiz.setDueDate(updatedQuiz.getDueDate());
            }
            if (updatedQuiz.getAttemptsAllowed() != null) {
                quiz.setAttemptsAllowed(updatedQuiz.getAttemptsAllowed());
            }
            if (updatedQuiz.getTimeLimitMinutes() != null) {
                quiz.setTimeLimitMinutes(updatedQuiz.getTimeLimitMinutes());
            }
            if (updatedQuiz.getVisibility() != null) {
                quiz.setVisibility(updatedQuiz.getVisibility());
            }
            quizRepository.save(quiz);
            return true;
        }
        return false;
    }

    public Optional<Quiz> getQuizById(int id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getQuizesByLessonId(int lessonId) {
        return quizRepository.findByLessonId(lessonId);
    }
}

