package com.example.repository;

import com.example.model.Account;
import com.example.model.Todo;

import java.util.List;

public interface AccountRepository {

    // save acc
    // update acc ( email )
    // delete acc ( id )
    // find acc number ( id )

    void saveAccount(Account account);

    Account findMyAccountByEmail(String email);

    Account findMyAccount(int accountId);

    void updateAccount(Account account);

    void deleteAccount(int accountId);

    List<Todo> getAllAccount ();



}
