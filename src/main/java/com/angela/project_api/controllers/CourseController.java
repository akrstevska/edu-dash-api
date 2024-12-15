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
import java.util.stream.Collectors;

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
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getCourseStatistics() {
        List<Course> courses = courseService.getALlCourses();
        Map<String, Object> statistics = new HashMap<>();
        int totalCourses = courses.size();

        List<Map<String, Object>> courseStats = courses.stream().map(course -> {
            Map<String, Object> courseStat = new HashMap<>();
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(course.getId());

            long completedEnrollments = enrollments.stream()
                    .filter(Enrollment::isCompletionStatus)
                    .count();

            long activeEnrollments = enrollments.stream()
                    .filter(enrollment -> !enrollment.isCompletionStatus())
                    .count();

            courseStat.put("courseId", course.getId());
            courseStat.put("courseName", course.getTitle());

            double completionRate = enrollments.isEmpty() ? 0 : (completedEnrollments * 100.0 / enrollments.size());
            courseStat.put("completionRate", completionRate);

            courseStat.put("popularity", enrollments.size());
            courseStat.put("enrollmentDeadline", course.getEnrollmentDeadline());

            courseStat.put("activeEnrollments", activeEnrollments);

            // Calculate grade percentages
            Map<String, Double> gradeDistribution = new HashMap<>();
            long totalEnrollments = enrollments.size();

            if (totalEnrollments > 0) {
                long gradeA = enrollments.stream().filter(e -> e.getGrade() >= 90 && e.getGrade() <= 100).count();
                long gradeB = enrollments.stream().filter(e -> e.getGrade() >= 80 && e.getGrade() <= 89).count();
                long gradeC = enrollments.stream().filter(e -> e.getGrade() >= 70 && e.getGrade() <= 79).count();
                long gradeD = enrollments.stream().filter(e -> e.getGrade() >= 60 && e.getGrade() <= 69).count();
                long gradeF = enrollments.stream().filter(e -> e.getGrade() < 60).count();

                gradeDistribution.put("A", gradeA * 100.0 / totalEnrollments);
                gradeDistribution.put("B", gradeB * 100.0 / totalEnrollments);
                gradeDistribution.put("C", gradeC * 100.0 / totalEnrollments);
                gradeDistribution.put("D", gradeD * 100.0 / totalEnrollments);
                gradeDistribution.put("F", gradeF * 100.0 / totalEnrollments);
            } else {
                gradeDistribution.put("A", 0.0);
                gradeDistribution.put("B", 0.0);
                gradeDistribution.put("C", 0.0);
                gradeDistribution.put("D", 0.0);
                gradeDistribution.put("F", 0.0);
            }

            courseStat.put("grades", gradeDistribution);

            return courseStat;
        }).collect(Collectors.toList());

        statistics.put("num", totalCourses);
        statistics.put("stats", courseStats);

        return ResponseEntity.ok(statistics);
    }


}
