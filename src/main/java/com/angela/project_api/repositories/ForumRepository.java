package com.angela.project_api.repositories;

import com.angela.project_api.models.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum, Integer> {
    List<Forum> findByCourseId(int courseId);
}
