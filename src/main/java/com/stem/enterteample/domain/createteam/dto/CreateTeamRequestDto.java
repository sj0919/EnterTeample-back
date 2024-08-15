package com.stem.enterteample.domain.createteam.dto;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class CreateTeamRequestDto {
    @NotNull(message="Account ID는 필수입니다")
    private Long accountId;

    @NotBlank(message="팀명은 필수입니다")
    private String teamName;

    @NotBlank(message="제출물은 필수입니다")
    private String teamContent;

    @NotNull(message="날짜는 필수입니다")
    private LocalDate dueDate;

    public CreateTeam toEntity(Account account){
        return CreateTeam.builder()
                .account(account)
                .teamName(this.teamName)
                .teamContent(this.teamContent)
                .dueDate(this.dueDate)
                .build();
    }
}
