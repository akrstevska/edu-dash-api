package com.angela.project_api.repositories;

import com.angela.project_api.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findByLessonId(int lessonId);
}
