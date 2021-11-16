package com.luna.fusion.user.vo;

import javax.validation.constraints.NotEmpty;

/**
 * @author Iszychen@win10
 * @date 2020/2/4 18:56
 */
public class UpdateMobileVO {

    @NotEmpty
    /** 新手机号 */
    private String newMobile;

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }
}
