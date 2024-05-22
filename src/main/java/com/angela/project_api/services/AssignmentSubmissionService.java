package com.angela.project_api.services;

import com.angela.project_api.models.Assignment;
import com.angela.project_api.models.AssignmentSubmission;
import com.angela.project_api.models.Student;
import com.angela.project_api.repositories.AssignmentRepository;
import com.angela.project_api.repositories.AssignmentSubmissionRepository;
import com.angela.project_api.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentSubmissionService {
    @Autowired
    private AssignmentSubmissionRepository assignmentSubmissionRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    
    public boolean createAssignmentSubmission(AssignmentSubmission assignmentSubmission) {
        Assignment assignment = assignmentRepository.findById(assignmentSubmission.getAssignment().getId()).orElse(null);
        Student student = studentRepository.findById(assignmentSubmission.getStudent().getId()).orElse(null);
        if (assignment != null && student!=null) {
            assignmentSubmission.setAssignment(assignment);
            assignmentSubmission.setStudent(student);
            assignmentSubmissionRepository.save(assignmentSubmission);
            return true;
        }
        return false;
    }


    public boolean deleteAssignmentSubmission(int id) {
        AssignmentSubmission assignmentSubmission = assignmentSubmissionRepository.findById(id).orElse(null);
        if (assignmentSubmission != null) {
            assignmentSubmissionRepository.delete(assignmentSubmission);
            return true;
        }
        return false;
    }

    public boolean updateAssignmentSubmission(int assignmentSubmissionId, AssignmentSubmission updatedAssignmentSubmission) {
        Optional<AssignmentSubmission> optionalAssignmentSubmission = assignmentSubmissionRepository.findById(assignmentSubmissionId);
        if (optionalAssignmentSubmission.isPresent()) {
            AssignmentSubmission assignmentSubmission = optionalAssignmentSubmission.get();
            if (updatedAssignmentSubmission.getAssignment() != null) {
                assignmentSubmission.setAssignment(updatedAssignmentSubmission.getAssignment());
            }
            if (updatedAssignmentSubmission.getSubmissionFormat() != null) {
                assignmentSubmission.setSubmissionFormat(updatedAssignmentSubmission.getSubmissionFormat());
            }
            if (updatedAssignmentSubmission.getStudent() != null) {
                assignmentSubmission.setStudent(updatedAssignmentSubmission.getStudent());
            }
            if (updatedAssignmentSubmission.getFileName() != null) {
                assignmentSubmission.setFileName(updatedAssignmentSubmission.getFileName());
            }
            if (updatedAssignmentSubmission.getFilePath() != null) {
                assignmentSubmission.setFilePath(updatedAssignmentSubmission.getFilePath());
            }
            if (updatedAssignmentSubmission.getSubmissionTime() != null) {
                assignmentSubmission.setSubmissionTime(updatedAssignmentSubmission.getSubmissionTime());
            }
            assignmentSubmissionRepository.save(assignmentSubmission);
            return true;
        }
        return false;
    }

    public Optional<AssignmentSubmission> getAssignmentSubmissionById(int id) {
        return assignmentSubmissionRepository.findById(id);
    }

    public List<AssignmentSubmission> getAssignmentSubmissionsByAssignmentId(int assignmentId) {
        return assignmentSubmissionRepository.findByAssignmentId(assignmentId);
    }
}

