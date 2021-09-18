package com.luna.fusion.user.vo;

import javax.validation.constraints.NotEmpty;

/**
 * @author Iszychen@win10
 * @date 2020/2/4 18:56
 */
public class UpdateEmailVO {

    @NotEmpty
    /** 新邮箱 */
    private String newEmail;

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
