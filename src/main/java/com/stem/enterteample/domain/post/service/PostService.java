package com.stem.enterteample.domain.post.service;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.account.service.AccountService;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import com.stem.enterteample.domain.createteam.service.CreateTeamService;
import com.stem.enterteample.domain.post.domain.Post;
import com.stem.enterteample.domain.post.dto.PostRequestDto;
import com.stem.enterteample.domain.post.repository.PostRepository;
import com.stem.enterteample.global.exception.CustomDeleteException;
import com.stem.enterteample.global.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stem.enterteample.global.exception.ErrorCode.PERMISSION_REJECTED_USER;
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountService accountService;
    private final CreateTeamService createTeamService;

    public Post createNewPost(PostRequestDto dto){
        Account account=accountService.findAccountById(Long.parseLong(dto.getAccountId()));
        CreateTeam team=createTeamService.findCreateTeamById(dto.getTeamId());
        Post post=dto.toEntity(account,team);
        Post savedPost=postRepository.save(post);
        return savedPost;
    }

    @Transactional(readOnly = true)
    public List<Post> findAllPostsByTeamId(Long teamId){
        List<Post>posts=postRepository.findAllByTeam_TeamId(teamId);
        return posts;
    }
    @Transactional(readOnly = true)
    public long countAllPostsByTeamId(Long teamId){
        return postRepository.countByTeam_TeamId(teamId);
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long postId){
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다.id"+postId));
        return post;
    }

    public void deletePost(Long postId,Long accountId){
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다.id"+postId));
        if(!postRepository.existsByPostIdAndAccount_AccountId(postId,accountId)){
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        postRepository.delete(post);
    }
}
