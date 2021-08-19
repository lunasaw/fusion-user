package com.iteknical.fusion.user.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Iszychen@win10
 * @date 2020/2/4 18:40
 */
public class UpdatePasswordVO {

    /** 密码 */
    @NotEmpty
    @Size(min = 8)
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
