package com.angela.project_api.controllers;

import com.angela.project_api.models.Course;
import com.angela.project_api.models.Lesson;
import com.angela.project_api.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("lesson")
@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @PostMapping
    public ResponseEntity<Map<String, Object>> addLesson(@RequestBody Lesson lesson) {
        boolean added = lessonService.createLesson(lesson);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Lesson added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Course not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getByCourse/{courseId}")
    public ResponseEntity<?> getLessonsByCourseId(@PathVariable int courseId) {
        List<Lesson> lessons = lessonService.getLessonsByCourseId(courseId);
        if (lessons != null && !lessons.isEmpty()) {
            return ResponseEntity.ok(lessons);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteLesson(@PathVariable int id) {
        boolean deleted = lessonService.deleteLesson(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Lesson deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Lesson not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateLesson(@PathVariable("id") int lessonId,
                                                   @RequestBody Lesson updatedLesson) {
        boolean updated = lessonService.updateLesson(lessonId, updatedLesson);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Lesson updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Lesson not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable int id) {
        Optional<Lesson> lesson = lessonService.getLessonById(id);
        if (lesson.isPresent()) {
            return ResponseEntity.ok(lesson.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
