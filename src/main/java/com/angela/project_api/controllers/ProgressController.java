package com.angela.project_api.controllers;

import com.angela.project_api.models.Progress;
import com.angela.project_api.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("progress")
@RestController
public class ProgressController {
    @Autowired
    private  ProgressService progressService;

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> saveProgress(@PathVariable("id") int progressId,
                                                            @RequestBody Progress updatedProgress) {
        boolean saved = progressService.saveProgress(progressId, updatedProgress);
        Map<String, Object> response = new HashMap<>();
        if (saved) {
            response.put("success", true);
            response.put("message", "Progress saved successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Progress not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getByEnrollment/{enrollmentId}")
    public ResponseEntity<?> getProgressByEnrollmentId(@PathVariable int enrollmentId) {
       Progress progress = progressService.getProgressByEnrollmentId(enrollmentId);
        if (progress != null) {
            return ResponseEntity.ok(progress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
