package com.angela.project_api.controllers;
import com.angela.project_api.models.Assignment;
import com.angela.project_api.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("assignment")
@RestController
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addAssignment(@RequestBody Assignment assignment) {
        boolean added = assignmentService.createAssignment(assignment);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Assignment added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Lesson not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteAssignment(@PathVariable int id) {
        boolean deleted = assignmentService.deleteAssignment(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Assignment deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Assignment not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAssignment(@PathVariable("id") int assignmentId, @RequestBody Assignment updatedAssignment) {
        boolean updated = assignmentService.updateAssignment(assignmentId, updatedAssignment);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Assignment updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Assignment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getByLesson/{lessonId}")
    public ResponseEntity<?> getAssignmentsByLessonId(@PathVariable int lessonId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByLessonId(lessonId);
        if (assignments != null && !assignments.isEmpty()) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable int id) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            return ResponseEntity.ok(assignment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}