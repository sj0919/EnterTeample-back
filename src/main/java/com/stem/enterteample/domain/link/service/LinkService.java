package com.stem.enterteample.domain.link.service;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.account.service.AccountService;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import com.stem.enterteample.domain.createteam.service.CreateTeamService;
import com.stem.enterteample.domain.link.domain.Link;
import com.stem.enterteample.domain.link.dto.LinkRequestDto;
import com.stem.enterteample.domain.link.dto.LinkResponseDto;
import com.stem.enterteample.domain.link.repository.LinkRepository;
import com.stem.enterteample.domain.post.domain.Post;
import com.stem.enterteample.global.exception.CustomDeleteException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stem.enterteample.global.exception.ErrorCode.PERMISSION_REJECTED_USER;

@Service
@Transactional
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;
    private final AccountService accountService;
    private final CreateTeamService createTeamService;

    public Link createNewLink(LinkRequestDto dto){
        Account account=accountService.findAccountById(Long.parseLong(dto.getAccountId()));
        CreateTeam team=createTeamService.findCreateTeamById(dto.getTeamId());
        Link link=dto.toEntity(account,team);
        Link savedLink=linkRepository.save(link);
        return savedLink;
    }

    @Transactional(readOnly=true)
    public List<Link> findAllLinksByTeamId(Long teamId){
        List<Link>links=linkRepository.findAllByTeam_TeamId(teamId);
        return links;
    }

    @Transactional(readOnly = true)
    public long countAllLinksByTeamId(Long teamId){
        return linkRepository.countByTeam_TeamId(teamId);
    }

    public void deleteLink(Long linkId,Long accountId){
        Link link=linkRepository.findById(linkId)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 link를 찾을 수 없습니댜.id"+linkId));
        if(!linkRepository.existsByLinkIdAndAccount_AccountId(linkId,accountId)){
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        linkRepository.delete(link);
    }
}
