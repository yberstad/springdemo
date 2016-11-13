package com.driw.account;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AccountTest {
    private static final String ACCOUNT_NAME = "test_account";
    private static final String PASSWORD = "password1";
    @Test
    public void setPassword_EncryptPassword() throws Exception {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        Account account = new Account(ACCOUNT_NAME, PASSWORD, roles);
        assertFalse("Password is not encrypted", account.getPassword().equals(PASSWORD));
    }
}