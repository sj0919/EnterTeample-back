package com.stem.enterteample.domain.createteam.service;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.account.service.AccountService;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import com.stem.enterteample.domain.createteam.dto.CreateTeamRequestDto;
import com.stem.enterteample.domain.createteam.dto.CreateTeamResponseDto;
import com.stem.enterteample.domain.createteam.repository.CreateTeamRepository;
import com.stem.enterteample.global.exception.CustomDeleteException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.stem.enterteample.global.exception.ErrorCode.PERMISSION_REJECTED_USER;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateTeamService {
    private final CreateTeamRepository createTeamRepository;
    private final AccountService accountService;

    public CreateTeam createNewTeam(CreateTeamRequestDto dto){
        Account account=accountService.findAccountById(dto.getAccountId());
        CreateTeam createTeam=dto.toEntity(account);
        CreateTeam savedTeam=createTeamRepository.save(createTeam);
        return savedTeam;
    }
    @Transactional(readOnly=true)
    public List<CreateTeam> findAllCreateTeam(){
        List<CreateTeam> createTeams=createTeamRepository.findAll();
        return createTeams;
    }

    @Transactional(readOnly = true)
    public long countAllCreateTeams(){
        return createTeamRepository.count();
    }

    @Transactional(readOnly=true)
    public CreateTeam findCreateTeamById(Long teamId){
        CreateTeam createTeam=createTeamRepository.findById(teamId)
                .orElseThrow(()->new EntityNotFoundException("해당 아이디를 가진 Team을 찾을 수 없습니다.id="+teamId));
        return createTeam;
    }

    public void deleteCreateTeam(Long teamId,Long accountId){
        CreateTeam createTeam=createTeamRepository.findById(teamId)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다.id"+teamId));
        if(!createTeamRepository.existsByTeamIdAndAccount_AccountId(teamId,accountId)){
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        createTeamRepository.delete(createTeam);

    }
}
