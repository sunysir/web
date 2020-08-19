package bean.api.account;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

public class UpdateUserInfoModel {
    @Expose
    private String name;
    @Expose
    private String portrait;
    @Expose
    private String desc;
    @Expose
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public static boolean check(UpdateUserInfoModel model){
        if (model!=null
                && !Strings.isNullOrEmpty(model.getName())
                && !Strings.isNullOrEmpty(model.getPortrait())
                && !Strings.isNullOrEmpty(model.getDesc())
                && model.getSex()!=0){
            return true;
        }
        return false;
    }
}
