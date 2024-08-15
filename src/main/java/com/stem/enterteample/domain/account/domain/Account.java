package com.stem.enterteample.domain.account.domain;

import com.stem.enterteample.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.stem.enterteample.domain.account.domain.AccountStatus.*;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
public class Account extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="account_id",updatable=false)
    private Long accountId;

    @Column(nullable=false,length=60)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false,updatable = false,length=16)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Builder
    public Account(Long accountId,String email,String password,String nickname){
        this.accountId=accountId;
        this.email=email;
        this.password=password;
        this.nickname=nickname;
        this.status=REGISTERED;
    }

    public void updateAccount(String nickname,String password){
        this.nickname=nickname;
        this.password=password;
    }

    public void withdrawAccount(){
        this.status=UNREGISTERED;
    }
}
