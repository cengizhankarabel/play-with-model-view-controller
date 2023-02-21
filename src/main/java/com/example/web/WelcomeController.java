package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/com/welcome"
    )
    public @ResponseBody String doWelcome(){
        return "welcome to spring-web";

    }
}
