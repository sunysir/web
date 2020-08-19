package bean.api.account;

import bean.card.UserCard;
import bean.db.User;
import com.google.gson.annotations.Expose;

public class AccountRspModel {
    @Expose
    private String account;

    @Expose
    private UserCard user;

    @Expose
    private String token;

    //标示是否绑定设备pushId
    @Expose
    private boolean isBind;

    public AccountRspModel(User user){
        this(user, false);
    }

    public AccountRspModel(User user, boolean isBind){
        this.user = new UserCard(user);
        this.account = user.getPhone();
        this.isBind = isBind;
        this.token = user.getToken();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getIsBind() {
        return isBind;
    }

    public void setIsBind(boolean isBind) {
        this.isBind = isBind;
    }
}
