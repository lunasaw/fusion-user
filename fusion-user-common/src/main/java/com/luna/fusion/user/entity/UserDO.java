package com.luna.fusion.user.entity;

import java.util.Date;

/**
 * user实体
 * 
 * @author Luna
 */
public class UserDO {

    /** 主键 */
    private long   id;
    /** 创建时间 */
    private Date   createTime;
    /** 修改时间 */
    private Date   modifiedTime;
    /** 版本 */
    private int    version;
    /** 邮箱 */
    private String email;
    /** 手机 */
    private String mobile;
    /** 密码 */
    private String password;
    /** 站点列表 */
    private String sites;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserDO{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", version=" + version +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", password='" + password + '\'' +
            ", sites='" + sites + '\'' +
            '}';
    }
}
