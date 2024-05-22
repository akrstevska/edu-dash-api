package com.angela.project_api.controllers;

import com.angela.project_api.models.Instructor;
import com.angela.project_api.models.Student;
import com.angela.project_api.services.InstructorService;
import com.angela.project_api.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("instructor")
@RestController
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addInstructor(@RequestBody Instructor instructor) {
        boolean added = instructorService.createInstructor(instructor);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Instructor added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "No data provided");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteInstructor(@PathVariable int id) {
        boolean deleted = instructorService.deleteInstructor(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Instructor deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Instructor not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateInstructor(@PathVariable("id") int instructorId,
                                                            @RequestBody Instructor updatedInstructor) {
        boolean updated = instructorService.updateInstructor(instructorId, updatedInstructor);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Instructor updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Instructor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping()
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

}

