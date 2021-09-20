package com.luna.fusion.user.req;

import javax.validation.constraints.NotBlank;

/**
 * @author Iszychen@win10
 * @date 2020/2/16 18:53
 */
public class TagReq {

    /** 标志名称 */
    private String name;

    @NotBlank
    private String userMask;

    public String getUserMask() {
        return userMask;
    }

    public void setUserMask(String userMask) {
        this.userMask = userMask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagVO{" +
            "name='" + name + '\'' +
            ", userMask='" + userMask + '\'' +
            '}';
    }
}
