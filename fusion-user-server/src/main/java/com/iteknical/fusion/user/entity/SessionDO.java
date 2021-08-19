package com.iteknical.fusion.user.entity;

import java.util.Date;

/**
 * session实体
 *
 * @author MrZhang-YUBO
 */

public class SessionDO {

    /** 主键 */
    private long   id;
    /** 创建时间 */
    private Date   createTime;
    /** 修改时间 */
    private Date   modifiedTime;
    /** 乐观锁 */
    private int  version;

    /** key */
    private String key;
    /** 用户id */
    private long   userId;
    /** 最后访问时间 */
    private Date   lastVisit;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }
}
