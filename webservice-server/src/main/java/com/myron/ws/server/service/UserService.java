package com.myron.ws.server.service;

import com.myron.ws.server.model.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public interface UserService {
    @WebMethod
    String getName(@WebParam(name = "userId") String userId);

    @WebMethod
    User getUser(String userI);

    @WebMethod
    ArrayList<User> getAlLUser();
}