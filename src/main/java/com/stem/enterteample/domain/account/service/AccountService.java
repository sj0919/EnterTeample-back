package com.stem.enterteample.domain.account.service;

import com.stem.enterteample.domain.account.domain.Account;
import com.stem.enterteample.domain.account.dto.AccountUpdateRequestDto;
import com.stem.enterteample.domain.account.dto.SignUpRequestDto;
import com.stem.enterteample.domain.account.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;


    public Long signUp(SignUpRequestDto requestDto){
        if(existsByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다"+requestDto.getEmail());
        }
        Account account=accountRepository.save(requestDto.toEntity());
        return account.getAccountId();
    }

    @Transactional(readOnly=true)
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Account를 찾을 수 없습니다.id="+ id));
    }

    public Long update(Long accountId, AccountUpdateRequestDto requestDto) {
        Account account = findAccountById(accountId);
        account.updateAccount(requestDto.getNickname(),requestDto.getPassword());
        return account.getAccountId();
    }

    @Transactional
    public void withdraw(Long accountId) {
        Account account = findAccountById(accountId);
        account.withdrawAccount();
    }

    public void delete(Long accountId) {
        Account account = findAccountById(accountId);
        accountRepository.delete(account);
    }
}
