package service;

import bean.api.account.AccountRspModel;
import bean.api.account.LoginModel;
import bean.api.account.RegisterModel;
import bean.api.base.ResponseModel;
import bean.card.UserCard;
import bean.db.User;
import com.google.common.base.Strings;
import factory.UserFactory;
import javafx.application.Application;
import org.glassfish.jersey.internal.inject.Custom;

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
    public String get() {
        return "You get the login!";
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> getRegister(RegisterModel model) {
        //判断是否有账户
        User user = UserFactory.findPhone(model.getAccount());
        if (user != null) {
            return ResponseModel.buildHaveAccountError();
        }
        //判断是否有用户名
        user = UserFactory.findName(model.getName());
        if (user != null){
            return ResponseModel.buildHaveNameError();
        }
        //进行注册逻辑
        user = UserFactory.register(model.getAccount(), model.getPassword(), model.getName());
        if (user != null) {
            if (!Strings.isNullOrEmpty(model.getPushId())){
                return bind(user, model.getPushId());
            }
            return ResponseModel.buildOk(new AccountRspModel(user));
        }else {
            return ResponseModel.buildRegisterError();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> getLogin(LoginModel model){
        if (!LoginModel.check(model)){
            return ResponseModel.LoginParasError();
        }
        User user = UserFactory.login(model.getAccount(), model.getPassword());
        if (user!=null){
            String pushId = model.getPushId();
            if (pushId != null){
                return bind(user, pushId);
            }
            return ResponseModel.buildOk(new AccountRspModel(user));
        }else {
            return ResponseModel.buildLoginError();
        }
    }

    @POST
    @Path("/bind/{pushId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> bind(@HeaderParam("token") String token, @PathParam("pushId") String pushId){
        if (Strings.isNullOrEmpty(token) && Strings.isNullOrEmpty(pushId)){
            return ResponseModel.LoginParasError();
        }
        User user = UserFactory.findToken(token);
        if (user != null){
            return bind(user, pushId);
        }
        return ResponseModel.buildServiceError();
    }

    private static ResponseModel<AccountRspModel> bind(User self, String pushId){
        self = UserFactory.bindPushId(self, pushId);
        if (self != null){
            AccountRspModel accountRspModel = new AccountRspModel(self);
            accountRspModel.setIsBind(true);
            return ResponseModel.buildOk(accountRspModel);
        }else {
            return ResponseModel.buildServiceError();
        }
    }
}
