package com.iteknical.fusion.user.dao;

import org.apache.ibatis.annotations.*;

import com.iteknical.fusion.user.entity.TagDO;

/**
 * @author Iszychen@win10
 * @date 2020/2/13 18:12
 */
@Mapper
public interface TagDAO {

    /**
     * 插入
     *
     * @param tagDO
     */
    @Insert("INSERT INTO tb_tag(create_time, name, sequence, mark) VALUES (now(), #{name}, #{sequence}, #{mark})")
    // @Options(useGeneratedKeys = true, tagNameProperty = "id", tagNameColumn = "id")
    void insert(TagDO tagDO);

    /**
     * 通过tag查找tagDO
     * 
     * @param name
     * @return
     */
    @Select("SELECT id, create_time, name, sequence, mark FROM tb_tag WHERE name=#{name}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "name", column = "name"),
        @Result(property = "sequence", column = "sequence"),
        @Result(property = "mark", column = "mark"),
    })
    TagDO get(@Param("name") String name);
}
