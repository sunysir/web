package service;

import bean.api.account.UpdateUserInfoModel;
import bean.api.base.ResponseModel;
import bean.card.UserCard;
import bean.db.User;
import factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserService {
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseModel<UserCard> updateInfo(@HeaderParam("token") String token, UpdateUserInfoModel model) {
        if (!UpdateUserInfoModel.check(model)) {
            return ResponseModel.LoginParasError();
        }
        User user = UserFactory.findToken(token);
        if (user != null){
            user = UserFactory.updateInfo(user, model.getName(), model.getPortrait(), model.getDesc(), model.getSex());
            if (user!=null){
                UserCard card = new UserCard(user);
                return ResponseModel.buildOk(card);
            }
            return ResponseModel.buildServiceError();
        }
        return ResponseModel.buildLoginError();
    }
}
