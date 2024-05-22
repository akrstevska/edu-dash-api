package com.angela.project_api.services;

import com.angela.project_api.models.Assignment;
import com.angela.project_api.models.Assignment;
import com.angela.project_api.models.AssignmentSubmission;
import com.angela.project_api.models.Lesson;
import com.angela.project_api.repositories.AssignmentRepository;
import com.angela.project_api.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LessonRepository lessonRepository;
    
    public boolean createAssignment(Assignment assignment) {
        Lesson lesson = lessonRepository.findById(assignment.getLesson().getId()).orElse(null);

        if (lesson != null) {
            assignment.setLesson(lesson);
            assignmentRepository.save(assignment);
            return true;
        }
        return false;
    }


    public boolean deleteAssignment(int id) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment != null) {
            assignmentRepository.delete(assignment);
            return true;
        }
        return false;
    }

    public boolean updateAssignment(int assignmentId, Assignment updatedAssignment) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);
        if (optionalAssignment.isPresent()) {
            Assignment assignment = optionalAssignment.get();
            if (updatedAssignment.getTitle() != null) {
                assignment.setTitle(updatedAssignment.getTitle());
            }
            if (updatedAssignment.getDescription() != null) {
                assignment.setDescription(updatedAssignment.getDescription());
            }
            if (updatedAssignment.getDeadLine() != null) {
                assignment.setDeadline(updatedAssignment.getDeadLine());
            }
            if (updatedAssignment.getLesson() != null) {
                assignment.setLesson(updatedAssignment.getLesson());
            }

            assignmentRepository.save(assignment);
            return true;
        }
        return false;
    }

    public Optional<Assignment> getAssignmentById(int id) {
        return assignmentRepository.findById(id);
    }

    public List<Assignment> getAssignmentsByLessonId(int lessonId) {
        return assignmentRepository.findByLessonId(lessonId);
    }
}

