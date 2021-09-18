package com.luna.fusion.user.dao;

import com.luna.fusion.user.entity.UserDO;
import org.apache.ibatis.annotations.*;

/**
 * @author Luna
 * @Description userDO Mapper
 * @date 2019年8月28日 下午5:54:35
 */
@Mapper
public interface UserDAO {

    /**
     * 插入
     *
     * @param userDO
     */
    @Insert("INSERT INTO tb_user(email, password, create_time, modified_time, mobile, sites, version) VALUES (#{email}, #{password}, now(), now(), #{mobile}, #{sites}, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(UserDO userDO);

    /**
     * 更新密码
     * 
     * @param userDO
     * @return
     */
    @Update("UPDATE tb_user SET modified_time=now(), password=#{password}, version=version+1 WHERE id=#{id} AND version=#{version}")
    int updatePassword(UserDO userDO);

    /**
     * 更新
     *
     * @param userDO
     * @return
     */
    @Update("UPDATE tb_user SET modified_time=now(), email=#{email}, mobile=#{mobile}, sites=#{sites}, version=version+1 WHERE id=#{id} AND version=#{version}")
    int update(UserDO userDO);

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @Delete("DELETE FROM tb_user WHERE id=#{id}")
    int delete(@Param("id") long id);

    /**
     * 通过id查找userDO
     *
     * @param id
     * @return
     */
    @Select("SELECT id, email, create_time, modified_time, mobile, sites, version FROM tb_user WHERE id=#{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "email", column = "email"),
        @Result(property = "mobile", column = "mobile"),
        @Result(property = "sites", column = "sites"),
        @Result(property = "version", column = "version")
    })
    UserDO get(@Param("id") long id);

    /**
     * 通过邮箱获取userDO
     *
     * @param email
     * @return
     */
    @Select("SELECT id, email, create_time, modified_time, mobile, sites, version FROM tb_user WHERE email=#{email}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "email", column = "email"),
        @Result(property = "mobile", column = "mobile"),
        @Result(property = "sites", column = "sites"),
        @Result(property = "version", column = "version")
    })
    UserDO getByEmail(@Param("email") String email);

    /**
     * 通过邮箱和密码获取userDO
     *
     * @param email
     * @param password
     * @return
     */
    @Select("SELECT id, email, create_time, modified_time, mobile, sites, version FROM tb_user WHERE email=#{email} AND password=#{password}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "email", column = "email"),
        @Result(property = "mobile", column = "mobile"),
        @Result(property = "sites", column = "sites"),
        @Result(property = "version", column = "version")
    })
    UserDO getByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    /**
     * 通过手机和密码获取userDO
     *
     * @param mobile
     * @param password
     * @return
     */
    @Select("SELECT id, email, create_time, modified_time, mobile, sites, version FROM tb_user WHERE mobile=#{mobile} AND password=#{password}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "email", column = "email"),
        @Result(property = "mobile", column = "mobile"),
        @Result(property = "sites", column = "sites"),
        @Result(property = "version", column = "version")
    })
    UserDO getByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    /**
     * 通过手机获取userDO
     *
     * @param mobile
     * @return
     */
    @Select("SELECT id, email, create_time, modified_time, mobile, sites, version FROM tb_user WHERE mobile=#{mobile} ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "email", column = "email"),
        @Result(property = "mobile", column = "mobile"),
        @Result(property = "sites", column = "sites"),
        @Result(property = "version", column = "version")
    })
    UserDO getByMobile(@Param("mobile") String mobile);
}
