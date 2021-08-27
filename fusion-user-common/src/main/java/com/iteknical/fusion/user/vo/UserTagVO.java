package com.iteknical.fusion.user.vo;

import java.util.Date;
import java.util.Set;

/**
 * @author Iszychen@win10
 * @date 2020/2/16 18:53
 */
public class UserTagVO {

    /** 标志名称 */
    private String      TagName;

    /** 主键 */
    private Long        id;
    /** 用户创建时间 */
    private Date        createTime;
    /** 权限修改时间 */
    private Date        modifiedTime;
    /** 邮箱 */
    private String      email;
    /** 手机 */
    private String      mobile;
    /** 站点列表 */
    private Set<String> siteSet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<String> getSiteSet() {
        return siteSet;
    }

    public void setSiteSet(Set<String> siteSet) {
        this.siteSet = siteSet;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    @Override
    public String toString() {
        return "UserTagVO{" +
            "TagName='" + TagName + '\'' +
            ", id=" + id +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", siteSet=" + siteSet +
            '}';
    }
}
