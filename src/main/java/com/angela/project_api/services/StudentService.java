package com.angela.project_api.services;


import com.angela.project_api.models.Course;
import com.angela.project_api.models.Lesson;
import com.angela.project_api.models.Student;
import com.angela.project_api.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public boolean createStudent(Student student) {
        if (student != null) {
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;
    }

    public boolean updateStudent(int studentId, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (updatedStudent.getFirstName() != null) {
                student.setFirstName(updatedStudent.getFirstName());
            }
            if (updatedStudent.getLastName() != null) {
                student.setLastName(updatedStudent.getLastName());
            }
            if (updatedStudent.getAge() != null) {
                student.setAge(updatedStudent.getAge());
            }
            if (updatedStudent.getCurrentYear() != null) {
                student.setCurrentYear(updatedStudent.getCurrentYear());
            }
            if (updatedStudent.getEmail() != null) {
                student.setEmail(updatedStudent.getEmail());
            }
            studentRepository.save(student);
            return true;
        }
        return false;
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
