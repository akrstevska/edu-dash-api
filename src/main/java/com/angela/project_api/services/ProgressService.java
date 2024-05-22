package com.angela.project_api.services;

import com.angela.project_api.models.Progress;
import com.angela.project_api.repositories.CourseRepository;
import com.angela.project_api.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private CourseRepository courseRepository;

    public boolean saveProgress(int progressId, Progress updatedProgress) {
        Optional<Progress> optionalProgress = progressRepository.findById(progressId);
        if (optionalProgress.isPresent()) {
            Progress progress = optionalProgress.get();
            if (updatedProgress.getEnrollment() != null) {
                progress.setEnrollment(updatedProgress.getEnrollment());
            }
            if (updatedProgress.getCompletedLessons() != null) {
                progress.setCompletedLessons(updatedProgress.getCompletedLessons());
            }
            if (updatedProgress.getCurrentLesson() != null) {
                progress.setCurrentLesson(updatedProgress.getCurrentLesson());
            }
            if (updatedProgress.getLastAccess() != null) {
                progress.setLastAccess(updatedProgress.getLastAccess());
            }
            calculateOverallProgress(progress);
            progressRepository.save(progress);
            return true;
        }
        return false;
    }

    public Progress getProgressByEnrollmentId(int enrollmentId){
        return progressRepository.findByEnrollmentId(enrollmentId);
    }
    private void calculateOverallProgress(Progress progress) {
        int totalLessons = progress.getEnrollment().getCourse().getLessons().size();
        int completedLessons = progress.getCompletedLessons().size();

        if (totalLessons > 0) {
            float overallProgress = ((float) completedLessons / totalLessons) * 100;
            progress.setOverallProgress(overallProgress);
        } else {
            progress.setOverallProgress(0);
        }
    }


}
