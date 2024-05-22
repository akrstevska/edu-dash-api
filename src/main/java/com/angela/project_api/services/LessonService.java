package com.angela.project_api.services;

import com.angela.project_api.models.Course;
import com.angela.project_api.models.Lesson;
import com.angela.project_api.models.Progress;
import com.angela.project_api.repositories.CourseRepository;
import com.angela.project_api.repositories.LessonRepository;
import com.angela.project_api.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ProgressRepository progressRepository;
    public boolean createLesson(Lesson lesson) {
        Course course = courseRepository.findById(lesson.getCourse().getId()).orElse(null);

        if (course != null) {
            lesson.setCourse(course);
            lessonRepository.save(lesson);
            return true;
        }
        return false;

    }
    public List<Lesson> getLessonsByCourseId(int courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

public boolean deleteLesson(int id) {
    Optional<Lesson> optionalLesson = lessonRepository.findById(id);
    if (optionalLesson.isPresent()) {
        Lesson lesson = optionalLesson.get();

        List<Progress> progressList = progressRepository.findByCompletedLessonsId(id);
        for (Progress progress : progressList) {
            progress.getCompletedLessons().removeIf(l -> l.getId() == id);
            progressRepository.save(progress);
        }
        List<Progress> currentLessonList = progressRepository.findByCurrentLessonId(id);
        for (Progress progress : currentLessonList) {
            progress.setCurrentLesson(null);
            progressRepository.save(progress);
        }
        lessonRepository.delete(lesson);
        return true;
    }
    return false;
}

    public boolean updateLesson(int lessonId, Lesson updatedLesson) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            if (updatedLesson.getTitle() != null) {
                lesson.setTitle(updatedLesson.getTitle());
            }
            if (updatedLesson.getDescription() != null) {
                lesson.setDescription(updatedLesson.getDescription());
            }
            if (updatedLesson.getContent() != null) {
                lesson.setContent(updatedLesson.getContent());
            }
            if (updatedLesson.getCourse() != null) {
                lesson.setCourse(updatedLesson.getCourse());
            }

            lessonRepository.save(lesson);
            return true;
        }
        return false;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(int id) {
        return lessonRepository.findById(id);
    }
}
