package com.stem.enterteample.domain.createteam.dto;

import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class CreateTeamResponseDto {
    private Long teamId;
    private String teamName;
    private String teamContent;
    private LocalDate dueDate;
    private String writerName;

    public static CreateTeamResponseDto from(CreateTeam createTeam,String writerName) {
        return new CreateTeamResponseDto(
                createTeam.getTeamId(),
                createTeam.getTeamName(),
                createTeam.getTeamContent(),
                createTeam.getDueDate(),
                writerName
        );
    }
}
