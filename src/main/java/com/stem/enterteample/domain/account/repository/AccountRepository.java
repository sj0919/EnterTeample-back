package com.stem.enterteample.domain.account.repository;
import com.stem.enterteample.domain.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Boolean existsByEmail(String email);
}
