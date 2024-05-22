package com.angela.project_api.repositories;

import com.angela.project_api.models.Enrollment;
import com.angela.project_api.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByCourseId(int courseId);

    List<Enrollment> findByStudentId(int studentId);
}
