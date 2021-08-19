package com.iteknical.fusion.user.entity;

import java.util.Date;

/**
 * @author czy@win10
 * @date 2020/1/15 12:55
 */
public class UserTagDO {

    /** 编号 */
    private long id;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date modifiedTime;
    /** 乐观锁标志 */
    private int  version;

    /** 用户id */
    private long userId;
    /** 用户标志1 */
    private long tag1;
    /** 用户标志2 */
    private long tag2;
    /** 用户标志3 */
    private long tag3;
    /** 用户标志4 */
    private long tag4;
    /** 用户标志5 */
    private long tag5;

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

    public long getTag1() {
        return tag1;
    }

    public void setTag1(long tag1) {
        this.tag1 = tag1;
    }

    public long getTag2() {
        return tag2;
    }

    public void setTag2(long tag2) {
        this.tag2 = tag2;
    }

    public long getTag3() {
        return tag3;
    }

    public void setTag3(long tag3) {
        this.tag3 = tag3;
    }

    public long getTag4() {
        return tag4;
    }

    public void setTag4(long tag4) {
        this.tag4 = tag4;
    }

    public long getTag5() {
        return tag5;
    }

    public void setTag5(long tag5) {
        this.tag5 = tag5;
    }
}
