package com.angela.project_api.controllers;

import com.angela.project_api.models.Forum;
import com.angela.project_api.models.ForumPost;
import com.angela.project_api.services.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("forumPost")
@RestController
public class ForumPostController {
    @Autowired
    private ForumPostService forumPostService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addForumPost(@RequestBody ForumPost forumPost) {
        boolean added = forumPostService.createForumPost(forumPost);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Forum post added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum thread or Student not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteForumPost(@PathVariable int id) {
        boolean deleted = forumPostService.deleteForumPost(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Forum post deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum Post not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateForumPost(@PathVariable("id") int forumPostId, @RequestBody ForumPost updatedForumPost) {
        boolean updated = forumPostService.updateForumPost(forumPostId, updatedForumPost);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Forum Post updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ForumPost> getForumPostById(@PathVariable int id) {
        Optional<ForumPost> forumPost = forumPostService.getForumPostById(id);
        if (forumPost.isPresent()) {
            return ResponseEntity.ok(forumPost.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
