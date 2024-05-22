package com.angela.project_api.controllers;

import com.angela.project_api.models.Forum;
import com.angela.project_api.models.ForumThread;
import com.angela.project_api.services.ForumThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("forumThread")
@RestController
public class ForumThreadController {
    @Autowired
    private ForumThreadService forumThreadService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addForumThread(@RequestBody ForumThread forumThread) {
        boolean added = forumThreadService.createForumThread(forumThread);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Forum Thread added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum or Student not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteForumThread(@PathVariable int id) {
        boolean deleted = forumThreadService.deleteForumThread(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Forum thread and associated posts deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum Thread not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateForumThread(@PathVariable("id") int forumThreadId, @RequestBody ForumThread updatedForumThread) {
        boolean updated = forumThreadService.updateForumThread(forumThreadId, updatedForumThread);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Forum Thread updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Forum Thread not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumThread> getForumThreadById(@PathVariable int id) {
        Optional<ForumThread> forumThread = forumThreadService.getForumThreadById(id);
        if (forumThread.isPresent()) {
            return ResponseEntity.ok(forumThread.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
