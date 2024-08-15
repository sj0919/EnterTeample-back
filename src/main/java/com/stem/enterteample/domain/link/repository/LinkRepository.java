package com.stem.enterteample.domain.link.repository;

import com.stem.enterteample.domain.link.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link,Long> {
    boolean existsByLinkIdAndAccount_AccountId(Long linkId,Long accountId);
    List<Link> findAllByTeam_TeamId(Long teamId);
    long countByTeam_TeamId(Long teamId);
}
