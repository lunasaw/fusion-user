package com.iteknical.fusion.user.vo;

import javax.validation.constraints.NotBlank;


/**
 * @author Iszychen@win10
 * @date 2020/2/5 15:19
 */
public class ResetPasswordVO {

    /** 用户外标，邮箱或手机 */
    @NotBlank
    private String userMark;

	public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }
}
