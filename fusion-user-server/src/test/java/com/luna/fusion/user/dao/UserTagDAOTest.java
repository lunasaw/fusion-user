package com.luna.fusion.user.dao;

import javax.annotation.Resource;

import com.luna.fusion.user.dto.UserTagDTO;
import com.luna.fusion.user.entity.UserTagDO;
import com.luna.fusion.user.utils.DO2DTOUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author czy@win10
 * @date 2020/1/15 17:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTagDAOTest {

    private static final long USERID   = 90116;
    private static final long USERTAG1 = 10001;
    private static final long USERTAG2 = 10002;
    private static final long USERTAG3 = 10003;
    private static final long USERTAG4 = 10004;
    private static final long USERTAG5 = 10005;

    @Resource
    private UserTagDAO        userTagDAO;

    @Test
    public void aInsert() {
        UserTagDO userTagDO = new UserTagDO();
        userTagDO.setUserId(USERID);
        userTagDO.setTag1(USERTAG1);
        userTagDO.setTag2(USERTAG2);
        userTagDO.setTag3(USERTAG3);
        userTagDO.setTag4(USERTAG4);
        userTagDO.setTag5(USERTAG5);
        userTagDAO.insert(userTagDO);
    }

    @Test
    public void bget() {
        UserTagDO userTagDO = userTagDAO.get(95001);
        UserTagDTO userTagDTO = DO2DTOUtils.UserTagDO2UserTagDTO(userTagDO);
        Assert.assertNotNull(userTagDTO);
    }

    @Test
    public void cUpdate() {
        UserTagDO tagDO = userTagDAO.get(USERID);
        tagDO.setUserId(USERID);
        tagDO.setTag1(USERTAG1 + 1);
        tagDO.setTag2(USERTAG2 + 1);
        tagDO.setTag3(USERTAG3 + 1);
        tagDO.setTag4(USERTAG4 + 1);
        tagDO.setTag5(USERTAG5 + 1);
        int affectRow = userTagDAO.update(tagDO);

        Assert.assertEquals(1, affectRow);

        UserTagDO userTagDO = userTagDAO.get(tagDO.getUserId());
        Assert.assertNotSame(userTagDO.getUserId(), tagDO.getUserId());
        Assert.assertNotSame(userTagDO.getTag1(), tagDO.getTag1());
        Assert.assertNotSame(userTagDO.getTag2(), tagDO.getTag2());
        Assert.assertNotSame(userTagDO.getTag3(), tagDO.getTag3());
        Assert.assertNotSame(userTagDO.getTag4(), tagDO.getTag4());
        Assert.assertNotSame(userTagDO.getTag5(), tagDO.getTag5());
    }

    @Test
    public void dDelete() {
        int affectRow = userTagDAO.delete(USERID);

        Assert.assertEquals(1, affectRow);

        UserTagDO userTagDO = userTagDAO.get(USERID);
        Assert.assertNull(userTagDO);
    }
}
