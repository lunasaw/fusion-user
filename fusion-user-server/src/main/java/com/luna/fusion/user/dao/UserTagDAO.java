package com.luna.fusion.user.dao;

import com.luna.fusion.user.entity.UserTagDO;
import org.apache.ibatis.annotations.*;

/**
 * @author czy@win10
 * @date 2020/1/15 12:54
 */
@Mapper
public interface UserTagDAO {
    /**
     * 插入
     *
     * @param userTagDO
     */
    @Insert("INSERT INTO tb_user_tag(create_time, modified_time, version, user_id, tag_1, tag_2, tag_3, tag_4, tag_5) VALUES (now(), now(), 0, #{userId}, #{tag1}, #{tag2}, #{tag3}, #{tag4}, #{tag5})")
    // @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(UserTagDO userTagDO);

    /**
     * 通过userId查找用户标志
     *
     * @param userId 用户id
     * @return UserTagDO
     */
    @Select("SELECT id, create_time, modified_time, version, user_id, tag_1, tag_2, tag_3, tag_4, tag_5 FROM tb_user_tag WHERE user_id=#{userId}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "tag1", column = "tag_1"),
        @Result(property = "tag2", column = "tag_2"),
        @Result(property = "tag3", column = "tag_3"),
        @Result(property = "tag4", column = "tag_4"),
        @Result(property = "tag5", column = "tag_5")
    })
    UserTagDO get(@Param("userId") long userId);

    /**
     * 通过userId删除
     * 
     * @param userId
     * @return
     */
    @Delete("DELETE FROM tb_user_tag WHERE user_id=#{userId}")
    int delete(@Param("userId") long userId);

    /**
     * 更新
     * 
     * @param userTagDO
     * @return 影响行数
     */
    @Update("UPDATE tb_user_tag SET modified_time=now(), version=version+1, tag_1=#{tag1}, tag_2=#{tag2}, tag_3=#{tag3}, tag_4=#{tag4}, tag_5=#{tag5} WHERE user_id=#{userId} and version=#{version}")
    int update(UserTagDO userTagDO);
}
