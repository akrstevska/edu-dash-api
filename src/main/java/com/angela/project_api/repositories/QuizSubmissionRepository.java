package com.angela.project_api.repositories;

import com.angela.project_api.models.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Integer> {
    List<QuizSubmission> findByQuizId(int quizId);
}
