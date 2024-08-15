package com.stem.enterteample.domain.post.dto;

import com.stem.enterteample.domain.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class PostResponseDto {
    private Long postId;
    private String writerName;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static PostResponseDto from(Post post,String writerName){
        return new PostResponseDto(
                post.getPostId(),
                writerName,
                post.getContent(),
                post.getCreatedDate(),
                post.getModifiedDate()
        );
    }
}
