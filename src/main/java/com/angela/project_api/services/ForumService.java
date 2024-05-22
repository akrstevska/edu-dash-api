package com.angela.project_api.services;

import com.angela.project_api.models.Course;
import com.angela.project_api.models.Enrollment;
import com.angela.project_api.models.Forum;
import com.angela.project_api.models.Instructor;
import com.angela.project_api.repositories.CourseRepository;
import com.angela.project_api.repositories.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ForumRepository forumRepository;

    public boolean createForum(Forum forum) {
        Course course = courseRepository.findById(forum.getCourse().getId()).orElse(null);
        if (course != null) {
            forum.setCourse(course);
            forumRepository.save(forum);
            return true;
        }
        return false;

    }
    public boolean deleteForum(int id) {
        Forum forum = forumRepository.findById(id).orElse(null);
        if (forum != null) {
            forumRepository.delete(forum);
            return true;
        }
        return false;
    }
    public boolean updateForum(int forumId, Forum updatedForum) {
        Optional<Forum> optionalForum = forumRepository.findById(forumId);
        if (optionalForum.isPresent()) {
            Forum forum = optionalForum.get();
            if (updatedForum.getTitle() != null) {
                forum.setTitle(updatedForum.getTitle());
            }
            if (updatedForum.getDescription() != null) {
                forum.setDescription(updatedForum.getDescription());
            }
            if (updatedForum.getCategory() != null) {
                forum.setCategory(updatedForum.getCategory());
            }
            if (updatedForum.getCourse() != null) {
                forum.setCourse(updatedForum.getCourse());
            }
            if (updatedForum.getCreationDate() != null) {
                forum.setCreationDate(updatedForum.getCreationDate());
            }
            if (updatedForum.getLastActivity() != null) {
                forum.setLastActivity(updatedForum.getLastActivity());
            }

            forumRepository.save(forum);
            return true;
        }
        return false;
    }

    public List<Forum> getForumsByCourseId(int courseId) {
        return forumRepository.findByCourseId(courseId);
    }

    public Optional<Forum> getForumById(int id) {
        return forumRepository.findById(id);
    }
}
