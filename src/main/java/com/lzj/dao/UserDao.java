package com.lzj.dao;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Created by win7 on 2017-07-10.
 */

public class UserDao {

    public String delete(){
        return "delete";
    }

    public String find(){
        return "find";
    }

    public String insert(){
        return "insert";
    }
}
