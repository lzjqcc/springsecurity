package com.lzj.util;

import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 2017-07-10.
 */
public class MyUserDetails implements UserDetailsService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
         User user=null;
        if (username.equals("li")){
            GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_USER");
            list.add(authority);
            user = new User(username, "123", true, true, true, true,list);

        }else if (username.equals("admin")){
            GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_ADMIN");
            list.add(authority);
            user = new User(username, "123", true, true, true, true,list);

        }
        return user;
    }
}
