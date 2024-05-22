package com.angela.project_api.repositories;
import com.angela.project_api.models.Lesson;
import com.angela.project_api.models.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByCourseId(int courseId);

}
