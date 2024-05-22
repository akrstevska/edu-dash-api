package com.angela.project_api.controllers;
import com.angela.project_api.models.QuizSubmission;
import com.angela.project_api.services.QuizSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("quizSubmission")
@RestController
public class QuizSubmissionController {
    @Autowired
    private QuizSubmissionService quizSubmissionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addQuizSubmission(@RequestBody QuizSubmission quizSubmission) {
        boolean added = quizSubmissionService.createQuizSubmission(quizSubmission);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Quiz Submission added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Lesson not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteQuizSubmission(@PathVariable int id) {
        boolean deleted = quizSubmissionService.deleteQuizSubmission(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Quiz Submission deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Quiz Submission not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateQuizSubmission(@PathVariable("id") int quizSubmissionId, @RequestBody QuizSubmission updatedQuizSubmission) {
        boolean updated = quizSubmissionService.updateQuizSubmission(quizSubmissionId, updatedQuizSubmission);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Quiz Submission updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Quiz Submission not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getByQuiz/{quizId}")
    public ResponseEntity<?> getQuizSubmissionsByQuizId(@PathVariable int quizId) {
        List<QuizSubmission> quizSubmissions = quizSubmissionService.getQuizSubmissionsByQuizId(quizId);
        if (quizSubmissions != null && !quizSubmissions.isEmpty()) {
            return ResponseEntity.ok(quizSubmissions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizSubmission> getQuizSubmissionById(@PathVariable int id) {
        Optional<QuizSubmission> quizSubmission = quizSubmissionService.getQuizSubmissionById(id);
        if (quizSubmission.isPresent()) {
            return ResponseEntity.ok(quizSubmission.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}