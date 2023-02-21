package com.example.web;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.logging.Logger;

@RestController
public class AccountController {

    static Logger logger= Logger.getLogger("TodoController");

    @Autowired
    private AccountRepository accountRepository;
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


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/com/accounts/{accId}"
    )
    public ResponseEntity<Object> getAccount(@PathVariable(name = "accId")int accId){
        Account account= accountRepository.findMyAccount(accId);
        if(account!=null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(account.getFirstName());
    }

}
