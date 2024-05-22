package com.angela.project_api.controllers;

import com.angela.project_api.models.Enrollment;
import com.angela.project_api.models.ForumThread;
import com.angela.project_api.models.Question;
import com.angela.project_api.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("question")
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addQuestion(@RequestBody Question question) {
        boolean added = questionService.createQuestion(question);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Question added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Quiz not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteQuestion(@PathVariable int id) {
        boolean deleted = questionService.deleteQuestion(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Question deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Question not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateQuestion(@PathVariable("id") int questionId, @RequestBody Question updatedQuestion) {
        boolean updated = questionService.updateQuestion(questionId, updatedQuestion);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Question updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Question not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getByQuiz/{quizId}")
    public ResponseEntity<?> getQuestionsByQuizId(@PathVariable int quizId) {
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        if (questions != null && !questions.isEmpty()) {
            return ResponseEntity.ok(questions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
