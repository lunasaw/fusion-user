package com.iteknical.fusion.user.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iteknical.fusion.user.entity.SessionDO;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionDAOTest {

    private static final long   USER_ID = 10001;
    private static final String KEY     = "abcde";

    @Resource
    private SessionDAO          sessionDAO;

    @Test
    public void aTestInsert() {
        SessionDO sessionDO = new SessionDO();
        sessionDO.setUserId(USER_ID);
        sessionDO.setKey(KEY);

        sessionDAO.insert(sessionDO);
    }

    @Test
    public void cTestGet() {
        SessionDO sessionDO = sessionDAO.get(KEY);
        Assert.assertNotNull(sessionDO);
        Assert.assertNotNull(sessionDO.getId());
        Assert.assertEquals(USER_ID, sessionDO.getUserId());
        Assert.assertEquals(KEY, sessionDO.getKey());
    }

    @Test
    public void dTestDelete() {
        int affectRow = sessionDAO.delete(KEY);
        Assert.assertEquals(1, affectRow);
        SessionDO sessionDO = sessionDAO.get(KEY);
        Assert.assertNull(sessionDO);
    }

    @Test
    public void eTestRefulsh() {
        SessionDO sessionDO = sessionDAO.get(KEY);
        sessionDAO.refresh(sessionDO);
    }
}
