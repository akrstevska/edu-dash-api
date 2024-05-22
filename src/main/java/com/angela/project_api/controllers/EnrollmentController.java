package com.angela.project_api.controllers;

import com.angela.project_api.models.Enrollment;
import com.angela.project_api.models.Student;
import com.angela.project_api.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("enrollment")
@RestController
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @PostMapping("/enroll")
    public ResponseEntity<Map<String, Object>> enrollStudent(@RequestBody Enrollment enrollment) {
        boolean enrolled = enrollmentService.enrollStudent(enrollment);
        Map<String, Object> response = new HashMap<>();
        if (enrolled) {
            response.put("success", true);
            response.put("message", "Student enrolled successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Student or course not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getByCourse/{courseId}")
    public ResponseEntity<?> getEnrollmentsByCourseId(@PathVariable int courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
        if (enrollments != null && !enrollments.isEmpty()) {
            return ResponseEntity.ok(enrollments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByStudent/{studentId}")
    public ResponseEntity<?> getEnrollmentsByStudentId(@PathVariable int studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        if (enrollments != null && !enrollments.isEmpty()) {
            return ResponseEntity.ok(enrollments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEnrollment(@PathVariable int id) {
        boolean deleted = enrollmentService.deleteEnrollment(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Enrollment deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Enrollment not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEnrollment(@PathVariable("id") int enrollmentId,
                                                             @RequestBody Enrollment updatedEnrollment) {
        boolean updated = enrollmentService.updateEnrollment(enrollmentId, updatedEnrollment);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Enrollment updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Enrollment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}

