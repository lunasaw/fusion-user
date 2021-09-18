package com.luna.fusion.user.entity;

import java.util.Date;

/**
 * @author Iszychen@win10
 * @date 2020/2/13 18:12
 */
public class TagDO {

    /** 编号 */
    private long   id;
    /** 创建时间 */
    private Date   createTime;

    /** 标志名称 */
    private String name;

    private int    sequence;
    private int    mark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
