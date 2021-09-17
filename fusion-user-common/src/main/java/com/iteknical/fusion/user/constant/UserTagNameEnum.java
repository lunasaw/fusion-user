package com.iteknical.fusion.user.constant;

/**
 * UserTagName常量
 * <p>
 * 此处的常量只是为了使用者开发方便，具体的全集以表中为准
 * </p>
 * 
 * @author chenzhangyue@weidian.com
 * 2021/9/17
 */
public enum UserTagNameEnum {

    /** 是否为管理员 */
    IS_ADMIN(7, UserTagNameConstant.IS_ADMIN, 1, 2),
    /** 密码是否失效 */
    IS_PASSWORD_EXPIRE(8, UserTagNameConstant.IS_PASSWORD_EXPIRE, 1, 1),
    /** 是否是hashcat测试用户 */
    IS_HASHCAT_TESTER(9, UserTagNameConstant.IS_HASHCAT_TESTER, 1, 4),
    ;

    /**
     * 权限标识
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String  name;

    /**
     * 序列号
     */
    private Integer sequence;

    /**
     * 所属值
     */
    private Integer mark;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Integer getMark() {
        return mark;
    }

    public static boolean isValidCode(Integer code) {
        for (UserTagNameEnum typeEnums : values()) {
            if (typeEnums.getId().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static UserTagNameEnum getByCode(Integer code) {
        for (UserTagNameEnum typeEnums : values()) {
            if (typeEnums.getId().equals(code)) {
                return typeEnums;
            }
        }
        return null;
    }

    UserTagNameEnum(Integer id, String name, Integer sequence, Integer mark) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
        this.mark = mark;
    }
}
