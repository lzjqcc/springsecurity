package com.lzj.controller;

import org.omg.IOP.ServiceContextHolder;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by win7 on 2017-07-10.
 */
@Controller
public class HelloWordController {
    @RequestMapping("/loginx")
    public String index(){
        //AbstractUserDetailsAuthenticationProvider
        System.out.print("springmvc");
        return "login";
    }
    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("username",getPrincipal());
        return "admin/admin";
    }
    @RequestMapping("/user")
    public String user(Model model){
        model.addAttribute("username",getPrincipal());
        return "user/index";
    }
    private String getPrincipal(){
        String userName=null;
        Object object=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDetails){
            userName=((UserDetails)object).getUsername();
        }else {
            userName=object.toString();
        }
        return userName;
    }

}


