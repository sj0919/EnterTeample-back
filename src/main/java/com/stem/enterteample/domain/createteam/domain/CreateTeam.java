package com.stem.enterteample.domain.createteam.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class CreateTeam extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="team_id",updatable = false)
    private Long teamId;

    @ManyToOne
    @JoinColumn(name="account_id",updatable=false)
    private Account account;

    @Column(nullable=false)
    private String teamName;

    @Column(nullable=false)
    private String teamContent;

    @Column(nullable=false)
    private LocalDate dueDate;

    @Transient
    private LocalDateTime updatedAt;

    @Builder
    public CreateTeam(Account account, String teamName, String teamContent, LocalDate dueDate){
        this.account=account;
        this.teamName=teamName;
        this.teamContent=teamContent;
        this.dueDate=dueDate;
    }
}
