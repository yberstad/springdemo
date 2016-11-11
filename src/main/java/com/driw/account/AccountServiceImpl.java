package com.driw.account;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component("accountService")
@Transactional
class AccountServiceImpl implements AccountService {

    // Seems that Mockito to not work when using final...?
    private AccountRepository accountRepository;

    //@autowire is not necessary because we only have one constructor.
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }
}
