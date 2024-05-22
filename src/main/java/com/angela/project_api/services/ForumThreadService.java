package com.angela.project_api.services;

import com.angela.project_api.models.Forum;
import com.angela.project_api.models.ForumThread;
import com.angela.project_api.models.Student;
import com.angela.project_api.repositories.ForumRepository;
import com.angela.project_api.repositories.ForumThreadRepository;
import com.angela.project_api.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForumThreadService {

    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private ForumThreadRepository forumThreadRepository;
    @Autowired
    private StudentRepository studentRepository;

    public boolean createForumThread(ForumThread forumThread) {
        Forum forum = forumRepository.findById(forumThread.getForum().getId()).orElse(null);
        Student student = studentRepository.findById(forumThread.getStudent().getId()).orElse(null);
        if (forum != null && student!=null) {
            forumThread.setForum(forum);
            forumThread.setStudent(student);
            forumThreadRepository.save(forumThread);
            return true;
        }
        return false;

    }
    public boolean deleteForumThread(int id) {
        ForumThread forumThread = forumThreadRepository.findById(id).orElse(null);
        if (forumThread != null) {
            forumThreadRepository.delete(forumThread);
            return true;
        }
        return false;
    }
    public boolean updateForumThread(int forumThreadId, ForumThread updatedForumThread) {
        Optional<ForumThread> optionalForumThread = forumThreadRepository.findById(forumThreadId);
        if (optionalForumThread.isPresent()) {
            ForumThread forumThread = optionalForumThread.get();
            if (updatedForumThread.getTitle() != null) {
                forumThread.setTitle(updatedForumThread.getTitle());
            }
            if (updatedForumThread.getCategory() != null) {
                forumThread.setCategory(updatedForumThread.getCategory());
            }
            if (updatedForumThread.getStudent() != null) {
                forumThread.setStudent(updatedForumThread.getStudent());
            }
            if (updatedForumThread.getCreationDate() != null) {
                forumThread.setCreationDate(updatedForumThread.getCreationDate());
            }
            if (updatedForumThread.getLastActivity() != null) {
                forumThread.setLastActivity(updatedForumThread.getLastActivity());
            }
            if (updatedForumThread.getForum() != null) {
                forumThread.setForum(updatedForumThread.getForum());
            }

            forumThreadRepository.save(forumThread);
            return true;
        }
        return false;
    }

    public Optional<ForumThread> getForumThreadById(int id) {
        return forumThreadRepository.findById(id);
    }
}
