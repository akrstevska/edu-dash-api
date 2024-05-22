package com.angela.project_api.repositories;

import com.angela.project_api.models.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Integer> {
    List<AssignmentSubmission> findByAssignmentId(int assignmentId);
}
