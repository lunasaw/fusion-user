package com.luna.fusion.user.dao;

import java.util.List;

import com.luna.fusion.user.entity.SessionDO;
import org.apache.ibatis.annotations.*;

/**
 * @author MrZhang-YUBO
 * @Description sessionDO Mapper
 * @date 2020年1月19日 下午6:21:05
 */
@Mapper
public interface SessionDAO {

    /**
     * 插入
     *
     * @param sessionDO
     */
    @Insert("INSERT INTO tb_session(create_time, modified_time, version, `key`, user_id, last_visit) VALUES (now(), now(), 0, #{key}, #{userId}, now())")
    // @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(SessionDO sessionDO);

    /**
     * 刷新session
     * <p>
     * 实际上就是更新最后访问时间
     * </p>
     * 
     * @param sessionDO
     * @return
     */
    @Update("UPDATE tb_session SET last_visit=now(), modified_time=now(), version=version+1 where version=#{version} and `key`=#{key}")
    int refresh(SessionDO sessionDO);

    /**
     * 删除
     * 
     * @param key
     * @return
     */
    @Delete("DELETE FROM tb_session WHERE `key`=#{key}")
    int delete(@Param("key") String key);

    /**
     * 通过key查找sessionDO
     * 
     * @param key
     * @return
     */
    @Select("SELECT id, create_time, modified_time, version, `key`, user_id, last_visit FROM tb_session WHERE `key`=#{key}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "key", column = "key"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "lastVisit", column = "last_visit")
    })
    SessionDO get(@Param("key") String key);

    /**
     * 列出过期session
     * <p>
     * 目前当做上次访问时间在6小时之前的当做过期session
     * <p/>
     * 
     * @return
     */
    @Select("SELECT id, create_time, modified_time, version, `key`, user_id, last_visit FROM tb_session WHERE last_visit<=date_sub(now(), interval #{hour} hour)")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "key", column = "key"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "lastVisit", column = "last_visit")
    })
    List<SessionDO> listExpiredSession(@Param("hour") int hour);
}
