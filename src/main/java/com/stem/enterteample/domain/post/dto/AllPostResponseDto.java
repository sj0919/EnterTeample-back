package com.stem.enterteample.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllPostResponseDto {
    private List<PostResponseDto> posts;
    private long count;
}
