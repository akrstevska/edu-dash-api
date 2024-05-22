package com.angela.project_api.services;

import com.angela.project_api.models.Quiz;
import com.angela.project_api.models.QuizSubmission;
import com.angela.project_api.models.Student;
import com.angela.project_api.repositories.QuizRepository;
import com.angela.project_api.repositories.QuizSubmissionRepository;
import com.angela.project_api.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizSubmissionService {
    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private StudentRepository studentRepository;
    
    public boolean createQuizSubmission(QuizSubmission quizSubmission) {
        Quiz quiz = quizRepository.findById(quizSubmission.getQuiz().getId()).orElse(null);
        Student student = studentRepository.findById(quizSubmission.getStudent().getId()).orElse(null);
        if (quiz != null && student!=null) {
            quizSubmission.setQuiz(quiz);
            quizSubmission.setStudent(student);
            quizSubmissionRepository.save(quizSubmission);
            return true;
        }
        return false;
    }


    public boolean deleteQuizSubmission(int id) {
        QuizSubmission quizSubmission = quizSubmissionRepository.findById(id).orElse(null);
        if (quizSubmission != null) {
            quizSubmissionRepository.delete(quizSubmission);
            return true;
        }
        return false;
    }

    public boolean updateQuizSubmission(int quizSubmissionId, QuizSubmission updatedQuizSubmission) {
        Optional<QuizSubmission> optionalQuizSubmission = quizSubmissionRepository.findById(quizSubmissionId);
        if (optionalQuizSubmission.isPresent()) {
            QuizSubmission quizSubmission = optionalQuizSubmission.get();
            if (updatedQuizSubmission.getQuiz() != null) {
                quizSubmission.setQuiz(updatedQuizSubmission.getQuiz());
            }
            if (updatedQuizSubmission.getSubmissionTime() != null) {
                quizSubmission.setSubmissionTime(updatedQuizSubmission.getSubmissionTime());
            }
            if (updatedQuizSubmission.getStudent() != null) {
                quizSubmission.setStudent(updatedQuizSubmission.getStudent());
            }
            if (updatedQuizSubmission.getDurationMinutes() != null) {
                quizSubmission.setDurationMinutes(updatedQuizSubmission.getDurationMinutes());
            }
            if (updatedQuizSubmission.getScore() != null) {
                quizSubmission.setScore(updatedQuizSubmission.getScore());
            }
            if (updatedQuizSubmission.getStatus() != null) {
                quizSubmission.setStatus(updatedQuizSubmission.getStatus());
            }


            quizSubmissionRepository.save(quizSubmission);
            return true;
        }
        return false;
    }

    public Optional<QuizSubmission> getQuizSubmissionById(int id) {
        return quizSubmissionRepository.findById(id);
    }

    public List<QuizSubmission> getQuizSubmissionsByQuizId(int quizId) {
        return quizSubmissionRepository.findByQuizId(quizId);
    }
}

