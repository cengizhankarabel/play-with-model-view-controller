package com.example.repository;

import com.example.model.Account;
import com.example.model.Todo;

import java.util.List;

public class JdbcAccountRepository implements AccountRepository{
    @Override
    public void saveAccount(Account account) {

    }

    @Override
    public Account findMyAccountByEmail(String email) {
        return null;
    }

    @Override
    public Account findMyAccount(int accountId) {
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(int accountId) {

    }

    @Override
    public List<Todo> getAllAccount() {
        return null;
    }
}
