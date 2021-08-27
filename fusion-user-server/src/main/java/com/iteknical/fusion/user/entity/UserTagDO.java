package com.iteknical.fusion.user.entity;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * @author czy@win10
 * @date 2020/1/15 12:55
 */
public class UserTagDO {

    /** 编号 */
    private Long    id;
    /** 创建时间 */
    private Date    createTime;
    /** 修改时间 */
    private Date    modifiedTime;
    /** 乐观锁标志 */
    private Integer version;

    /** 用户id */
    private Long    userId;
    /** 用户标志1 */
    private Long    tag1;
    /** 用户标志2 */
    private Long    tag2;
    /** 用户标志3 */
    private Long    tag3;
    /** 用户标志4 */
    private Long    tag4;
    /** 用户标志5 */
    private Long    tag5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTag1() {
        return tag1;
    }

    public void setTag1(Long tag1) {
        this.tag1 = tag1;
    }

    public Long getTag2() {
        return tag2;
    }

    public void setTag2(Long tag2) {
        this.tag2 = tag2;
    }

    public Long getTag3() {
        return tag3;
    }

    public void setTag3(Long tag3) {
        this.tag3 = tag3;
    }

    public Long getTag4() {
        return tag4;
    }

    public void setTag4(Long tag4) {
        this.tag4 = tag4;
    }

    public Long getTag5() {
        return tag5;
    }

    public void setTag5(Long tag5) {
        this.tag5 = tag5;
    }
}
