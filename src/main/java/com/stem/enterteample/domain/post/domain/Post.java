package com.stem.enterteample.domain.post.domain;

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
public class Post extends BaseTimeEntity {
    @Id@GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="post_id",updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name="account_id",updatable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name="team_id",updatable=false)
    private CreateTeam team;

    @Column(nullable = false,length=1000)
    private String content;

    @Builder
    public Post(Account account,CreateTeam team, String content){
        this.account=account;
        this.team=team;
        this.content=content;
    }
}
