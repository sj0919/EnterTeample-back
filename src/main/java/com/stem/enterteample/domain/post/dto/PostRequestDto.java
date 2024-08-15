package com.stem.enterteample.domain.post.dto;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import com.stem.enterteample.domain.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostRequestDto {
    @NotBlank(message="계정 아이디는 필수입니다")
    private String accountId;

    @NotNull(message="팀 아이디는 필수입니다")
    private Long teamId;

    @NotBlank(message="내용은 필수입니다")
    private String content;

    public Post toEntity(Account account, CreateTeam team){
        return Post.builder()
                .account(account)
                .team(team)
                .content(this.content)
                .content(content)
                .build();
    }

}
