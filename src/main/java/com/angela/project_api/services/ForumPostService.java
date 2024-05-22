package com.angela.project_api.services;

import com.angela.project_api.models.*;
import com.angela.project_api.models.ForumPost;
import com.angela.project_api.repositories.ForumPostRepository;
import com.angela.project_api.repositories.ForumThreadRepository;
import com.angela.project_api.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForumPostService {

    @Autowired
    private ForumPostRepository forumPostRepository;
    @Autowired
    private ForumThreadRepository forumThreadRepository;
    @Autowired
    private StudentRepository studentRepository;

    public boolean createForumPost(ForumPost forumPost) {
        ForumThread forumThread = forumThreadRepository.findById(forumPost.getThread().getId()).orElse(null);
        Student student = studentRepository.findById(forumPost.getStudent().getId()).orElse(null);
        if (forumThread != null && student!=null) {
            forumPost.setThread(forumThread);
            forumPost.setStudent(student);
            forumPostRepository.save(forumPost);
            return true;
        }
        return false;

    }
    public boolean deleteForumPost(int id) {
        ForumPost forumPost = forumPostRepository.findById(id).orElse(null);
        if (forumPost != null) {
            forumPostRepository.delete(forumPost);
            return true;
        }
        return false;
    }
    public boolean updateForumPost(int forumPostId, ForumPost updatedForumPost) {
        Optional<ForumPost> optionalForumPost = forumPostRepository.findById(forumPostId);
        if (optionalForumPost.isPresent()) {
            ForumPost forumPost = optionalForumPost.get();
            if (updatedForumPost.getTitle() != null) {
                forumPost.setTitle(updatedForumPost.getTitle());
            }
            if (updatedForumPost.getContent() != null) {
                forumPost.setContent(updatedForumPost.getContent());
            }
            if (updatedForumPost.getStudent() != null) {
                forumPost.setStudent(updatedForumPost.getStudent());
            }
            if (updatedForumPost.getThread() != null) {
                forumPost.setThread(updatedForumPost.getThread());
            }
            if (updatedForumPost.getCreationDate() != null) {
                forumPost.setCreationDate(updatedForumPost.getCreationDate());
            }
            forumPostRepository.save(forumPost);
            return true;
        }
        return false;
    }

    public Optional<ForumPost> getForumPostById(int id) {
        return forumPostRepository.findById(id);
    }
}
