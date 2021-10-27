package com.luna.fusion.user.dao;

import javax.annotation.Resource;

import com.luna.fusion.user.entity.TagDO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Iszychen@win10
 * @date 2020/2/13 18:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagDAOTest {
    private static final int    TAG_NUMBER = 1;
    private static final int    TAG        = 1 << 0;
    private static final String TAG_NAME   = "IS_ADMIN";

    private static final String TAG_NAME_1 = "IS_PASSWORD_EXPIRE";

    @Resource
    private TagDAO              tagDAO;

    @Test
    public void aInsert() {
        TagDO tagDO = new TagDO();
        tagDO.setMark(TAG);
        tagDO.setName(TAG_NAME_1);
        tagDO.setSequence(TAG_NUMBER);
        tagDAO.insert(tagDO);
        TagDO tagDO1 = tagDAO.get(TAG_NAME);
        Assert.assertNotNull(tagDO1);
    }

    @Test
    public void bGet() {
        TagDO tagDO = tagDAO.get(TAG_NAME_1);
        Assert.assertNotNull(tagDO);
    }

}
