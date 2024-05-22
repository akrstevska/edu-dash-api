package com.angela.project_api.services;


import com.angela.project_api.models.Course;
import com.angela.project_api.models.Enrollment;
import com.angela.project_api.models.Progress;
import com.angela.project_api.models.Student;
import com.angela.project_api.repositories.CourseRepository;
import com.angela.project_api.repositories.EnrollmentRepository;
import com.angela.project_api.repositories.ProgressRepository;
import com.angela.project_api.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProgressRepository progressRepository;
    public boolean enrollStudent(Enrollment enrollment) {
        Student student = studentRepository.findById(enrollment.getStudent().getId()).orElse(null);
        Course course = courseRepository.findById(enrollment.getCourse().getId()).orElse(null);
        if (student != null && course!= null) {
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollmentRepository.save(enrollment);
            Progress initialProgress = new Progress();
            initialProgress.setEnrollment(enrollment);
            initialProgress.setOverallProgress(0);
            initialProgress.setLastAccess(new Date());
            progressRepository.save(initialProgress);
            return true;
        }
        return false;
    }

    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public boolean deleteEnrollment(int id) {
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);
        if (enrollment != null) {
            enrollmentRepository.delete(enrollment);
            return true;
        }
        return false;
    }

    public boolean updateEnrollment(int enrollmentId, Enrollment updatedEnrollment) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();
            if (updatedEnrollment.getEnrollmentDate() != null) {
                enrollment.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());
            }
            if (updatedEnrollment.getCourse() != null) {
                enrollment.setCourse(updatedEnrollment.getCourse());
            }
            if (updatedEnrollment.getGrade() != null) {
                enrollment.setGrade(updatedEnrollment.getGrade());
            }
            if (updatedEnrollment.getStudent() != null) {
                enrollment.setStudent(updatedEnrollment.getStudent());
            }
            enrollmentRepository.save(enrollment);
            return true;
        }
        return false;
    }
}
