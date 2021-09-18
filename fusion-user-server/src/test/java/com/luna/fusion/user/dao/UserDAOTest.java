package com.luna.fusion.user.dao;

import javax.annotation.Resource;

import com.luna.fusion.user.entity.UserDO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest {

    private static final String EMAIL           = "15696756582@qq.com";
    private static final String MOBILE          = "15696756582";
    private static final String PASSWORD        =
        "5sxaaaArqNy97T9QIPL2yoQifDHiwWagRT9Zj6OYsTB/EyRwCIks4nrpwGlO2+t7y1VS4BmxpbKPpp+e4yJF3g==";
    private static final String SITES           = "10002";
    private static final String CHANGE_PASSWORD = "fedcba2";

    @Resource
    private UserDAO             userDAO;

    @Test
    public void aTestInsert() {
        UserDO userDO = new UserDO();
        userDO.setPassword(PASSWORD);
        userDO.setSites(SITES);
        userDO.setMobile(MOBILE);
        userDO.setEmail(EMAIL);

        userDAO.insert(userDO);

        Assert.assertNotNull(userDO);
        Assert.assertNotNull(userDO.getId());
    }

    @Test
    public void bTestGetByEmail() {
        UserDO userDO = userDAO.getByEmail(EMAIL);

        Assert.assertNotNull(userDO);
        Assert.assertNotNull(userDO.getId());
        Assert.assertEquals(EMAIL, userDO.getEmail());
        Assert.assertEquals(SITES, userDO.getSites());
        Assert.assertEquals(MOBILE, userDO.getMobile());
        Assert.assertNull(userDO.getPassword());
    }

    @Test
    public void cTestGetByEmailAndPassword() {
        UserDO userDO = userDAO.getByEmailAndPassword(EMAIL, PASSWORD);

        Assert.assertNotNull(userDO);
        Assert.assertNotNull(userDO.getId());
        Assert.assertEquals(EMAIL, userDO.getEmail());
        Assert.assertEquals(SITES, userDO.getSites());
        Assert.assertEquals(MOBILE, userDO.getMobile());
        Assert.assertNull(userDO.getPassword());
    }

    @Test
    public void dTestGetByMobile() {
        UserDO userDO = userDAO.getByMobile(MOBILE);

        Assert.assertNotNull(userDO);
        Assert.assertNotNull(userDO.getId());
        Assert.assertEquals(EMAIL, userDO.getEmail());
        Assert.assertEquals(SITES, userDO.getSites());
        Assert.assertEquals(MOBILE, userDO.getMobile());
        Assert.assertNull(userDO.getPassword());
    }

    @Test
    public void eTestGetByMobileAndPassword() {
        UserDO userDO = userDAO.getByMobileAndPassword(MOBILE, PASSWORD);

        Assert.assertNotNull(userDO);
        Assert.assertNotNull(userDO.getId());
        Assert.assertEquals(EMAIL, userDO.getEmail());
        Assert.assertEquals(SITES, userDO.getSites());
        Assert.assertEquals(MOBILE, userDO.getMobile());
        Assert.assertNull(userDO.getPassword());
    }

    @Test
    public void fTestGetById() {
        UserDO userDO = userDAO.get(userDAO.getByMobileAndPassword(MOBILE, PASSWORD).getId());

        Assert.assertNotNull(userDO);
        Assert.assertNotNull(userDO.getId());
        Assert.assertEquals(EMAIL, userDO.getEmail());
        Assert.assertEquals(SITES, userDO.getSites());
        Assert.assertEquals(MOBILE, userDO.getMobile());
        Assert.assertNull(userDO.getPassword());
    }

    @Test
    public void gTestUpdatePassword() {
        UserDO userDO = userDAO.getByMobile(MOBILE);
        userDO.setPassword(CHANGE_PASSWORD);

        int affectRow = userDAO.updatePassword(userDO);

        Assert.assertEquals(1, affectRow);

        UserDO newUserDO = userDAO.getByMobileAndPassword(MOBILE, CHANGE_PASSWORD);
        Assert.assertNotNull(newUserDO);
    }

    @Test
    public void hTestDelete() {
        int affectRow = userDAO.delete(userDAO.getByMobile(MOBILE).getId());

        Assert.assertEquals(1, affectRow);

        UserDO userDO = userDAO.getByMobile(MOBILE);
        Assert.assertNull(userDO);
    }
}
