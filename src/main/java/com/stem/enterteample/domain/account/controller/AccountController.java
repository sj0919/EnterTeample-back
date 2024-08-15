package com.stem.enterteample.domain.account.controller;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.account.dto.AccountResponseDto;
import com.stem.enterteample.domain.account.dto.AccountUpdateRequestDto;
import com.stem.enterteample.domain.account.dto.SignUpRequestDto;
import com.stem.enterteample.domain.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    //계정 생성기능
    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public AccountResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Long id=accountService.signUp(requestDto);
        Account findAccount=accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

    //계정 조회기능
    @GetMapping("/{accountId}")
    @ResponseStatus(value=HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long accountId){
        Account findAccount=accountService.findAccountById(accountId);
        return AccountResponseDto.from(findAccount);
    }

    //계정 프로필 수정
    @PostMapping("/profile/{accountId}")
    @ResponseStatus(value=HttpStatus.OK)
    public AccountResponseDto update(@PathVariable final Long accountId,@RequestBody @Valid final AccountUpdateRequestDto requestDto){
        Long id=accountService.update(accountId,requestDto);
        Account findAccount=accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

    //계정 삭제(휴면 계정으로)
    @PatchMapping("/{accountId}")
    @ResponseStatus(value=HttpStatus.OK)
    public String withdraw(@PathVariable long accountId){
        accountService.withdraw(accountId);
        return "성공적으로 탈퇴되었습니다(휴면)";
    }

    //계정 삭제(db에서도 삭제)
    @DeleteMapping("/{accountId}")
    @ResponseStatus(value=HttpStatus.OK)
    public String delete(@PathVariable long accountId){
        accountService.delete(accountId);
        return "성공적으로 탈퇴되었습니다";
    }
}
