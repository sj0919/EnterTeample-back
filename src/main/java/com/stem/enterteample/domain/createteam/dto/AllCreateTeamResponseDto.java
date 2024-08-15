package com.stem.enterteample.domain.createteam.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllCreateTeamResponseDto {
    private List<CreateTeamResponseDto> createTeam;
    private long count;
}
