package com.driw.account;

import java.util.Optional;

public interface AccountService {
    Account save(Account account);
    Optional<Account> findByUsername(String username);
}
