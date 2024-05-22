package com.angela.project_api.services;

import com.angela.project_api.models.Course;
import com.angela.project_api.models.Instructor;
import com.angela.project_api.repositories.CourseRepository;
import com.angela.project_api.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    public boolean createCourse(Course course) {
        Instructor instructor = instructorRepository.findById(course.getInstructor().getId()).orElse(null);

        if (instructor != null) {
            course.setInstructor(instructor);
            courseRepository.save(course);
            return true;
        }
            return false;

    }

    public boolean deleteCourse(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            courseRepository.delete(course);
            return true;
        }
        return false;
    }

    public boolean updateCourse(int courseId, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            if (updatedCourse.getTitle() != null) {
                course.setTitle(updatedCourse.getTitle());
            }
            if (updatedCourse.getDescription() != null) {
                course.setDescription(updatedCourse.getDescription());
            }
            if (updatedCourse.getEnrollmentDeadline() != null) {
                course.setEnrollmentDeadline(updatedCourse.getEnrollmentDeadline());
            }
            if (updatedCourse.getInstructor() != null) {
                course.setInstructor(updatedCourse.getInstructor());
            }
            if (updatedCourse.getSemester() != null) {
                course.setSemester(updatedCourse.getSemester());
            }
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public List<Course> getALlCourses() {
        return courseRepository.findAll();
    }
}
