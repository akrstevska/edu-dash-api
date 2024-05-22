package com.angela.project_api.controllers;
import com.angela.project_api.models.AssignmentSubmission;
import com.angela.project_api.services.AssignmentSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("assignmentSubmission")
@RestController
public class AssignmentSubmissionController {
    @Autowired
    private AssignmentSubmissionService assignmentSubmissionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addAssignmentSubmission(@RequestBody AssignmentSubmission assignmentSubmission) {
        boolean added = assignmentSubmissionService.createAssignmentSubmission(assignmentSubmission);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Assignment Submission added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Assignment or Student not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteAssignmentSubmission(@PathVariable int id) {
        boolean deleted = assignmentSubmissionService.deleteAssignmentSubmission(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Assignment Submission deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Assignment Submission not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAssignmentSubmission(@PathVariable("id") int assignmentSubmissionId, @RequestBody AssignmentSubmission updatedAssignmentSubmission) {
        boolean updated = assignmentSubmissionService.updateAssignmentSubmission(assignmentSubmissionId, updatedAssignmentSubmission);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Assignment Submission updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Assignment Submission not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getByAssignment/{assignmentId}")
    public ResponseEntity<?> getAssignmentSubmissionsByAssignmentId(@PathVariable int assignmentId) {
        List<AssignmentSubmission> assignmentSubmissions = assignmentSubmissionService.getAssignmentSubmissionsByAssignmentId(assignmentId);
        if (assignmentSubmissions != null && !assignmentSubmissions.isEmpty()) {
            return ResponseEntity.ok(assignmentSubmissions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentSubmission> getAssignmentSubmissionById(@PathVariable int id) {
        Optional<AssignmentSubmission> assignmentSubmission = assignmentSubmissionService.getAssignmentSubmissionById(id);
        if (assignmentSubmission.isPresent()) {
            return ResponseEntity.ok(assignmentSubmission.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}