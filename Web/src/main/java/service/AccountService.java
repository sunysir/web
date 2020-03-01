package service;

import bean.db.User;
import javafx.application.Application;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

/**
 * @author suny
 */
//127.0.0.1/api/account/...
@Path("/account")
public class AccountService {

    //127.0.0.1/api/account
    @GET //get请求
    @Path("/login")
    public String get(){
        return "You get the login!";
    }

    @POST
    @Path("/login")
    //指定请求和返回的响应体的格式为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User getpost(){
        User u = new User();
        u.setName("suny");
        return u;
    }
}
