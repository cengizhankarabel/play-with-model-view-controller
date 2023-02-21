package com.example.service;

import com.example.model.Account;

public interface AccountService {

    void register(Account account);

    Account login(Account account);
}
