package com.angela.project_api.repositories;

import com.angela.project_api.models.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM question_options WHERE question_id = :questionId", nativeQuery = true)
    void deleteQuestionOptionsByQuestionId(@Param("questionId") int questionId);

    List<Question> findByQuizId(int quizId);
}
