package com.example.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.model.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AccountServiceImpl implements AccountService {

    static Logger logger= Logger.getLogger("AccountServiceImpl");

    @Autowired
    AccountRepository accountRepository;

//    public AccountServiceImpl(AccountRepository accountRepository){
//        this.accountRepository=accountRepository;
//    }

    @Override
    public void register(Account account) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, account.getPassword().toCharArray());
        account.setPassword(bcryptHashString);
        accountRepository.saveAccount(account);
    }

    @Override
    public Account login(Account account1) {
        logger.info("invoke Account login service----------------"+"email:"+account1.getEmail()+"    "+"password:"+account1.getPassword());
        Account checkAcc= accountRepository.findMyAccountByEmail(account1.getEmail());
        Account account=null;
        logger.info("----------account found from db-----"+checkAcc.getFirstName());
        if(checkAcc!=null){
            BCrypt.Result result = BCrypt.verifyer().verify(checkAcc.getPassword().toCharArray(), checkAcc.getPassword());
            if(result.verified){
                return checkAcc;
            }
        }
        
        return checkAcc;
    }


    }

