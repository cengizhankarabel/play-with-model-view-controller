package com.example.web;

import com.example.model.Account;
import com.example.model.Todo;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;



@RestController
public class AccountController {

    static Logger logger= Logger.getLogger("TodoController");

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;




    // get all accounts
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/com/accounts"
    )
    public Collection<Account> getAccList(){
        logger.info("Invoke all accounts");
        Collection<Account> accounts = accountRepository.getAllAccount();
        return accounts;
    }


    // get todos for a user
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/com/accounts/{accId}"
    )
    public Account getAccount(@PathVariable(name = "accId")int accId){
        Account account= accountRepository.findMyAccount(accId);

        return account;
    }


    // login
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/com/login"
    )
    public void login(@RequestBody  Account account){
        accountService.login(account);

    }




}
