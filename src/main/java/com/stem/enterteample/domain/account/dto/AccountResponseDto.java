package com.stem.enterteample.domain.account.dto;

import com.stem.enterteample.domain.account.domain.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class AccountResponseDto {
    private Long accountId;
    private String email;
    private String nickname;

    public AccountResponseDto(Long accountId,String email,String nickname){
        this.accountId=accountId;
        this.email=email;
        this.nickname=nickname;
    }

    public static AccountResponseDto from (Account account){
        return new AccountResponseDto(account.getAccountId(),
                account.getEmail(),
                account.getNickname());

    }
}
