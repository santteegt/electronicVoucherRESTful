package com.buzz.restfulservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.buzz.restfulservice.service.UserManagementModule;
import com.buzz.restfulservice.service.UserService;
import com.buzz.restfulservice.service.VoucherReceptorService;
 
@ApplicationPath("/")
public class ApplicationConfig extends Application {
    @SuppressWarnings("unchecked")
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(UserService.class, UserManagementModule.class, VoucherReceptorService.class));
    }
}