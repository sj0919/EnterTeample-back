package com.stem.enterteample.domain.link.domain;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import com.stem.enterteample.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Link extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="link_id",updatable = false)
    private Long linkId;

    @ManyToOne
    @JoinColumn(name="account_id",updatable=false)
    private Account account;

    @ManyToOne
    @JoinColumn(name="team_id",updatable=false)
    private CreateTeam team;

    @Column(nullable=false,length=100)
    private String title;

    @Column(nullable=false,length=500)
    private String content;

    @Builder
    public Link(Account account,String title,CreateTeam team,String content){
        this.account=account;
        this.title=title;
        this.team=team;
        this.content=content;
    }
}
