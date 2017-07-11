package com.lzj.util;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;

/**
 * Created by win7 on 2017-07-11.
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {

        return null;
    }

    public boolean supports(Class<?> clazz) {
        return false;
    }
}
