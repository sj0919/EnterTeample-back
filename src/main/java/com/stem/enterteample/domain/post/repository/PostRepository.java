package com.stem.enterteample.domain.post.repository;

import com.stem.enterteample.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    boolean existsByPostIdAndAccount_AccountId(Long postId,Long accountId);
    List<Post> findAllByTeam_TeamId(Long teamId);
    long countByTeam_TeamId(Long teamId);
}
