package com.angela.project_api.repositories;

import com.angela.project_api.models.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumPostRepository extends JpaRepository<ForumPost, Integer> {
}
