package com.angela.project_api.repositories;

import com.angela.project_api.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByLessonId(int lessonId);
}
