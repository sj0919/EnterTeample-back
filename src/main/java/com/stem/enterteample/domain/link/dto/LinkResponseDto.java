package com.stem.enterteample.domain.link.dto;

import com.stem.enterteample.domain.link.domain.Link;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class LinkResponseDto {
    private Long LinkId;
    private String writerName;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static LinkResponseDto from (Link link, String writerName){
        return new LinkResponseDto(
                link.getLinkId(),
                writerName,
                link.getTitle(),
                link.getContent(),
                link.getCreatedDate(),
                link.getModifiedDate()
        );
    }
}
