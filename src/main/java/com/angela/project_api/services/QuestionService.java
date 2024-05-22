package com.angela.project_api.services;

import com.angela.project_api.models.*;
import com.angela.project_api.repositories.QuestionRepository;
import com.angela.project_api.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;

    public boolean createQuestion(Question question) {
        Quiz quiz = quizRepository.findById(question.getQuiz().getId()).orElse(null);
        if (quiz != null) {
            question.setQuiz(quiz);
            questionRepository.save(question);
            return true;
        }
        return false;

    }
    public boolean deleteQuestion(int id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            questionRepository.deleteQuestionOptionsByQuestionId(question.getId());
            questionRepository.delete(question);
            return true;
        }
        return false;
    }

    public boolean updateQuestion(int questionId, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            if (updatedQuestion.getQuestionText() != null) {
                question.setQuestionText(updatedQuestion.getQuestionText());
            }
            if (updatedQuestion.getQuestionType() != null) {
                question.setQuestionType(updatedQuestion.getQuestionType());
            }
            if (updatedQuestion.getQuiz() != null) {
                question.setQuiz(updatedQuestion.getQuiz());
            }
            if (updatedQuestion.getCorrectAnswer() != null) {
                question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
            }
            if (updatedQuestion.getOptions() != null) {
                question.setOptions(updatedQuestion.getOptions());
            }
            questionRepository.save(question);
            return true;
        }
        return false;
    }
    public List<Question> getQuestionsByQuizId(int quizId) {
        return questionRepository.findByQuizId(quizId);
    }



}
