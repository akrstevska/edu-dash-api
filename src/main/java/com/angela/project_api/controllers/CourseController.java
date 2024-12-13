package com.angela.project_api.controllers;

import com.angela.project_api.models.Course;
import com.angela.project_api.models.Enrollment;
import com.angela.project_api.services.CourseService;
import com.angela.project_api.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("course")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addCourse(@RequestBody Course course) {
        boolean added = courseService.createCourse(course);
        Map<String, Object> response = new HashMap<>();
        if (added) {
            response.put("success", true);
            response.put("message", "Course added successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Instructor not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable int id) {
        boolean deleted = courseService.deleteCourse(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Course and associated lessons deleted successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Course not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCourse(@PathVariable("id") int courseId, @RequestBody Course updatedCourse) {
        boolean updated = courseService.updateCourse(courseId, updatedCourse);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("success", true);
            response.put("message", "Course updated successfully");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("success", false);
            response.put("message", "Course not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Course>> getALlCourses() {
        List<Course> courses = courseService.getALlCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getCourseStatistics() {
        List<Course> courses = courseService.getALlCourses();
        Map<String, Object> statistics = new HashMap<>();
        int totalCourses = courses.size();

        List<Map<String, Object>> courseStats = courses.stream().map(course -> {
            Map<String, Object> courseStat = new HashMap<>();
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(course.getId());
            long completedEnrollments = enrollments.stream()
                    .filter(enrollment -> enrollment.getProgress() != null && enrollment.getProgress().getOverallProgress() == 100)
                    .count();

            courseStat.put("courseId", course.getId());
            courseStat.put("courseName", course.getTitle());
            courseStat.put("completionRate", enrollments.isEmpty() ? 0 : (completedEnrollments * 100.0 / enrollments.size()));
            courseStat.put("popularity", enrollments.size());
            courseStat.put("enrollmentDeadline", course.getEnrollmentDeadline());
            return courseStat;
        }).toList();

        statistics.put("num", totalCourses);
        statistics.put("stats", courseStats);

        return ResponseEntity.ok(statistics);
    }

}
