package com.stem.enterteample.domain.link.dto;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import com.stem.enterteample.domain.link.domain.Link;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class LinkRequestDto {
    @NotBlank(message="게정 id는 필수입니다")
    private String accountId;

    @NotNull(message="팀 아이디는 필수입니다")
    private Long teamId;

    @NotBlank(message="제목은 필수입니다")
    private String title;

    @NotBlank(message="내용은 필수입니다")
    private String content;

    public Link toEntity(Account account, CreateTeam team){
        return Link.builder()
                .account(account)
                .team(team)
                .title(this.title)
                .content(this.content)
                .content(content)
                .build();
    }
}
