package com.stem.enterteample.domain.createteam.controller;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.createteam.domain.CreateTeam;
import com.stem.enterteample.domain.createteam.dto.AllCreateTeamResponseDto;
import com.stem.enterteample.domain.createteam.dto.CreateTeamRequestDto;
import com.stem.enterteample.domain.createteam.dto.CreateTeamResponseDto;
import com.stem.enterteample.domain.createteam.service.CreateTeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/createteam")
@RequiredArgsConstructor
public class CreateTeamController {

    private final CreateTeamService createTeamService;

    //팀 생성
    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public CreateTeamResponseDto createNewTeam(@RequestBody @Valid final CreateTeamRequestDto dto){
        CreateTeam savedTeam=createTeamService.createNewTeam(dto);
        return CreateTeamResponseDto.from(savedTeam,savedTeam.getAccount().getNickname());
    }

    //팀 조회_1개
    @GetMapping("{teamId}")
    public CreateTeamResponseDto getOneTeam(@PathVariable(name="teamId")Long teamId){
        CreateTeam createTeam=createTeamService.findCreateTeamById(teamId);
        return CreateTeamResponseDto.from(createTeam,createTeam.getAccount().getNickname());
    }

    //팀 조회_전체
    @GetMapping
    public AllCreateTeamResponseDto getAllCreateTeam(){
        List<CreateTeamResponseDto> list=new ArrayList<>();
        List<CreateTeam> createTeams=createTeamService.findAllCreateTeam();
        for(CreateTeam createTeam:createTeams){
            CreateTeamResponseDto dto=CreateTeamResponseDto.from(createTeam,createTeam.getAccount().getNickname());
            list.add(dto);
        }
        long count=createTeamService.countAllCreateTeams();
        return new AllCreateTeamResponseDto(list,count);
    }

    @DeleteMapping("/{teamId}")
    public String deleteCreateTeam(@PathVariable(name="teamId")Long teamId,@RequestParam(name="accountId") Long accountId){
        createTeamService.deleteCreateTeam(teamId,accountId);

        return "성공적으로 삭제되었습니다";
    }

}
