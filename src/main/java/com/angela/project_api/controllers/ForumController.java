package com.angela.project_api.controllers;

import com.angela.project_api.models.Course;
import com.angela.project_api.models.Enrollment;
import com.angela.project_api.models.Forum;
import com.angela.project_api.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("forum")
@RestController
public class ForumController {
    @Autowired
    private ForumService forumService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addForum(@RequestBody Forum forum) {
        boolean added = forumService.createForum(forum);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Forum added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Course not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteForum(@PathVariable int id) {
        boolean deleted = forumService.deleteForum(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Forum and associated threads and posts deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateForum(@PathVariable("id") int forumId, @RequestBody Forum updatedForum) {
        boolean updated = forumService.updateForum(forumId, updatedForum);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Forum updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getByCourse/{courseId}")
    public ResponseEntity<?> getForumsByCourseId(@PathVariable int courseId) {
        List<Forum> forums = forumService.getForumsByCourseId(courseId);
        if (forums != null && !forums.isEmpty()) {
            return ResponseEntity.ok(forums);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable int id) {
        Optional<Forum> forum = forumService.getForumById(id);
        if (forum.isPresent()) {
            return ResponseEntity.ok(forum.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
