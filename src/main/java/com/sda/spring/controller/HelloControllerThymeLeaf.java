package com.sda.spring.controller;

import com.sda.spring.model.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloControllerThymeLeaf {

    @RequestMapping("/")

    public String index() {
        return "start";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)

    public String getLoginForm () {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)

    public String login (@ModelAttribute(name = "loginForm")LoginForm loginForm, Model model) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("pass")) {
    return "home";
        } else {
    return "start";
        }

    }
}
