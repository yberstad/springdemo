package com.driw.security;

import com.driw.account.Account;
import com.driw.account.AccountService;
import com.driw.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private AccountService accountService;

    @Autowired
    CustomUserDetailService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.accountService.findByUsername(userName)
                .map(account ->
                        new User(account.getUsername(),
                                account.getPassword(),
                                AuthorityUtils.createAuthorityList(account.getRoleAsArray())))
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username +'" + userName + "' not found")
                );
    }
}
