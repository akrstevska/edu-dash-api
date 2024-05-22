package com.angela.project_api.services;

import com.angela.project_api.models.Instructor;
import com.angela.project_api.models.Student;
import com.angela.project_api.repositories.InstructorRepository;
import com.angela.project_api.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    public boolean deleteInstructor(int id) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor != null) {
            instructorRepository.delete(instructor);
            return true;
        }
        return false;
    }

    public boolean createInstructor(Instructor instructor) {
        if (instructor != null) {
            instructorRepository.save(instructor);
            return true;
        }
        return false;
    }

    public boolean updateInstructor(int instructorId, Instructor updatedInstructor) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
        if (optionalInstructor.isPresent()) {
            Instructor instructor = optionalInstructor.get();
            if (updatedInstructor.getFirstName() != null) {
                instructor.setFirstName(updatedInstructor.getFirstName());
            }
            if (updatedInstructor.getLastName() != null) {
                instructor.setLastName(updatedInstructor.getLastName());
            }
            if (updatedInstructor.getDepartment() != null) {
                instructor.setDepartment(updatedInstructor.getDepartment());
            }
            if (updatedInstructor.getEmail() != null) {
                instructor.setEmail(updatedInstructor.getEmail());
            }
            instructorRepository.save(instructor);
            return true;
        }
        return false;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }
}
