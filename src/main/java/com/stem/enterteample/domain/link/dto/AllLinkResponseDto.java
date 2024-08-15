package com.stem.enterteample.domain.link.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllLinkResponseDto {
    private List<LinkResponseDto> links;
    private long count;
}
