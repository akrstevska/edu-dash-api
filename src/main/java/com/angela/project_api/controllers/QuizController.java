package com.angela.project_api.controllers;
import com.angela.project_api.models.Quiz;
import com.angela.project_api.services.QuizService;
import com.angela.project_api.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("quiz")
@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addQuiz(@RequestBody Quiz quiz) {
        boolean added = quizService.createQuiz(quiz);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Quiz added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Lesson not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteQuiz(@PathVariable int id) {
        boolean deleted = quizService.deleteQuiz(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Quiz deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Quiz not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateQuiz(@PathVariable("id") int quizId, @RequestBody Quiz updatedQuiz) {
        boolean updated = quizService.updateQuiz(quizId, updatedQuiz);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Quiz updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Quiz not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getByLesson/{lessonId}")
    public ResponseEntity<?> getQuizesByLessonId(@PathVariable int lessonId) {
        List<Quiz> quizes = quizService.getQuizesByLessonId(lessonId);
        if (quizes != null && !quizes.isEmpty()) {
            return ResponseEntity.ok(quizes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isPresent()) {
            return ResponseEntity.ok(quiz.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}