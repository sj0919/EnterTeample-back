package com.stem.enterteample.domain.createteam.repository;

import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateTeamRepository extends JpaRepository<CreateTeam,Long> {
    boolean existsByTeamIdAndAccount_AccountId(
            Long teamId,
            Long accountId
    );
}
