package com.angela.project_api.repositories;

import com.angela.project_api.models.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Integer> {
}
