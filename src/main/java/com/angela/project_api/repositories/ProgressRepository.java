package com.angela.project_api.repositories;

import com.angela.project_api.models.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {
    Progress findByEnrollmentId(int enrollmentId);

    List<Progress> findByCurrentLessonId(int id);

    List<Progress> findByCompletedLessonsId(int id);
}
