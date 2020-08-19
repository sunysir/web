package bean.api.base;

import bean.api.account.AccountRspModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResponseModel<M> implements Serializable {
    // 成功
    public static final int SUCCEED = 1;
    // 未知错误
    public static final int ERROR_UNKNOWN = 0;

    // 没有找到用户信息
    public static final int ERROR_NOT_FOUND_USER = 4041;
    // 没有找到群信息
    public static final int ERROR_NOT_FOUND_GROUP = 4042;
    // 没有找到群成员信息
    public static final int ERROR_NOT_FOUND_GROUP_MEMBER = 4043;

    // 创建用户失败
    public static final int ERROR_CREATE_USER = 3001;
    // 创建群失败
    public static final int ERROR_CREATE_GROUP = 3002;
    // 创建群成员失败
    public static final int ERROR_CREATE_MESSAGE = 3003;

    // 请求参数错误
    public static final int ERROR_PARAMETERS = 4001;
    // 请求参数错误-已存在账户
    public static final int ERROR_PARAMETERS_EXIST_ACCOUNT = 4002;
    // 请求参数错误-已存在名称
    public static final int ERROR_PARAMETERS_EXIST_NAME = 4003;

    // 服务器错误
    public static final int ERROR_SERVICE = 5001;

    // 账户Token错误，需要重新登录
    public static final int ERROR_ACCOUNT_TOKEN = 2001;
    // 账户登录失败
    public static final int ERROR_ACCOUNT_LOGIN = 2002;
    // 账户注册失败
    public static final int ERROR_ACCOUNT_REGISTER = 2003;
    // 没有权限操作
    public static final int ERROR_ACCOUNT_NO_PERMISSION = 2010;

    @Expose
    private String message;

    @Expose
    private int code;

    @Expose
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now();

    @Expose
    private M result;

    public ResponseModel(){
        code = 1;
        message = "ok";
    }

    public ResponseModel(M result){
        this();
        this.result = result;
    }

    public ResponseModel(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ResponseModel(int code, String message, M result){
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public M getResult() {
        return result;
    }

    public void setResult(M result) {
        this.result = result;
    }

    public static <M> ResponseModel<M> buildHaveAccountError(){
        return new ResponseModel<M>(ERROR_PARAMETERS_EXIST_ACCOUNT, "Aready have is account.");
    }

    public static <M> ResponseModel<M> buildHaveNameError(){
        return new ResponseModel<M>(ERROR_PARAMETERS_EXIST_NAME, "Aready have is name.");
    }

    public static <M> ResponseModel<M> buildOk(M result){
        return new ResponseModel<M>(result);
    }

    public static <M> ResponseModel<M> buildRegisterError(){
        return new ResponseModel<>(ERROR_ACCOUNT_REGISTER, "Have this account.");
    }

    public static <M> ResponseModel<M> buildLoginError() {
        return new ResponseModel<M>(ERROR_ACCOUNT_LOGIN, "Account or password error.");
    }

    public static <M> ResponseModel<M> LoginParasError() {
        return new ResponseModel<M>(ERROR_ACCOUNT_LOGIN, "paras is error.");
    }

    public static <M> ResponseModel<M> buildServiceError() {
        return new ResponseModel<M>(ERROR_SERVICE, "Service Error.");
    }

}
