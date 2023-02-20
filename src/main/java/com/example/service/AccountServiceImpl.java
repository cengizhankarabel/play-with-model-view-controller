package com.example.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.model.Account;
import com.example.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @Override
    public void register(Account account) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, account.getPassword().toCharArray());
        account.setPassword(bcryptHashString);
        accountRepository.saveAccount(account);
    }

    @Override
    public Account login(String email, String password) {
        Account account=accountRepository.findMyAccountByEmail(email);
        if(account!=null){
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), account.getPassword());
            if(result.verified){
                return account;
            }
        }

        return account;
    }
}
