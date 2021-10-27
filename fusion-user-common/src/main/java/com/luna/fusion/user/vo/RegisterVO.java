package com.luna.fusion.user.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author czy@win10
 * @date 2020/1/20 21:14
 */
public class RegisterVO {

    /** 用户外标，邮箱或手机 */
    @NotBlank
    private String userMark;
    /** 密码 */
    @NotEmpty
    @Size(min = 8)
    private String password;
    /** 站点 */
    @NotBlank
    private String site;

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
