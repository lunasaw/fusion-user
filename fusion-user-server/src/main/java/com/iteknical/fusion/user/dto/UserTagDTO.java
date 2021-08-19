package com.iteknical.fusion.user.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Iszychen@win10
 * @date 2020/2/13 17:23
 */
public class UserTagDTO {

    /** 编号 */
    private long       id;
    /** 创建时间 */
    private Date       createTime;
    /** 修改时间 */
    private Date       modifiedTime;
    /** 乐观锁标志 */
    private int        version;
    /** 用户id */
    private long       userId;
    /** 用户标志 */
    private List<Long> userTags;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Long> getUserTags() {
        return userTags;
    }

    public void setUserTags(List<Long> userTags) {
        this.userTags = userTags;
    }

    @Override
    public String toString() {
        return "UserTagDTO{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", version=" + version +
            ", userId=" + userId +
            ", userTags=" + userTags +
            '}';
    }
}
