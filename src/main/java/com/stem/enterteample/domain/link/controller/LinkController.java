package com.stem.enterteample.domain.link.controller;

import com.stem.enterteample.domain.link.domain.Link;
import com.stem.enterteample.domain.link.dto.AllLinkResponseDto;
import com.stem.enterteample.domain.link.dto.LinkRequestDto;
import com.stem.enterteample.domain.link.dto.LinkResponseDto;
import com.stem.enterteample.domain.link.service.LinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/createteam/{teamId}/links")
public class LinkController {
    private final LinkService linkService;

    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public LinkResponseDto createNewLink(@RequestBody @Valid final LinkRequestDto dto){
        Link savedLink=linkService.createNewLink(dto);
        return LinkResponseDto.from(savedLink,savedLink.getAccount().getNickname());
    }

    @GetMapping
    public AllLinkResponseDto getALlLink(@PathVariable(name="teamId") Long teamId){
        List<LinkResponseDto> list=new ArrayList<>();
        List<Link>links=linkService.findAllLinksByTeamId(teamId);
        for(Link link:links){
            LinkResponseDto dto=LinkResponseDto.from(link,link.getAccount().getNickname());
            list.add(dto);
        }
        long count=linkService.countAllLinksByTeamId(teamId);
        return new AllLinkResponseDto(list,count);
    }

    @DeleteMapping("/{linkId}")
    public String deleteLink(@PathVariable(name="linkId") Long linkId,@RequestParam(name="accountId") Long accountId){
        linkService.deleteLink(linkId,accountId);

        return "성공적으로 삭제되었습니다";
    }
}
