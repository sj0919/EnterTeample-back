package com.stem.enterteample.domain.post.controller;

import com.stem.enterteample.domain.post.domain.Post;
import com.stem.enterteample.domain.post.dto.AllPostResponseDto;
import com.stem.enterteample.domain.post.dto.PostRequestDto;
import com.stem.enterteample.domain.post.dto.PostResponseDto;
import com.stem.enterteample.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("createteam/{teamId}/posts")
public class PostController {
    private final PostService postService;

    //게시글 작성
    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public PostResponseDto createNewPost(@RequestBody @Valid final PostRequestDto dto){
        Post savedPost=postService.createNewPost(dto);
        return PostResponseDto.from(savedPost,savedPost.getAccount().getNickname());
    }

    //게시글 전체조회
    @GetMapping
    public AllPostResponseDto getAllPosts(@PathVariable(name="teamId") Long teamId){
        List<PostResponseDto> list=new ArrayList<>();
        List<Post>posts=postService.findAllPostsByTeamId(teamId);
        for(Post post:posts){
            PostResponseDto dto=PostResponseDto.from(post,post.getAccount().getNickname());
            list.add(dto);
        }
        long count=postService.countAllPostsByTeamId(teamId);
        return new AllPostResponseDto(list,count);
    }
    //게시글 삭제
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable(name="postId")Long postId,@RequestParam(name="accountId")Long accountId){
        postService.deletePost(postId,accountId);
        return "성공적으로 삭제되었습니다";
    }
}
