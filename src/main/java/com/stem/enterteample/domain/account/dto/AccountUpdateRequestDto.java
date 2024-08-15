package com.stem.enterteample.domain.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class AccountUpdateRequestDto {
    @NotBlank(message="닉네임은 필수로 입력해야합니다.")
    private String nickname;
    private String password;

    @Builder
    public AccountUpdateRequestDto(String nickname,String password){
        this.nickname=nickname;
        this.password=password;
    }
}
