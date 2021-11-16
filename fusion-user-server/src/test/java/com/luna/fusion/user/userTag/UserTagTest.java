package com.luna.fusion.user.userTag;

import javax.annotation.Resource;

import com.luna.fusion.user.client.UserTagClient;
import com.luna.fusion.user.rest.UserTagRest;
import com.luna.fusion.user.vo.TagVO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Iszychen@win10
 * @date 2020/2/24 14:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTagTest {

    @Resource
    UserTagRest userTagRest;

    @Test
    public void hasTagTest() {
        UserTagClient userTagClient = new UserTagClient("http://localhost:8081");
        TagVO tagVO = new TagVO();
        tagVO.setName("IS_ADMIN");
        userTagClient.hasTag("4114c4eb73ac425e9c1966c4eb473cac", "wednesday", tagVO);
    }

    @Test
    public void removeTagTest() {
        UserTagClient userTagClient = new UserTagClient("http://localhost:8081");
        TagVO tagVO = new TagVO();
        tagVO.setName("IS_ADMIN");
        userTagClient.removeTag("4114c4eb73ac425e9c1966c4eb473cac", "wednesday", tagVO);
    }

    @Test
    public void addTagTest() {
        UserTagClient userTagClient = new UserTagClient("http://localhost:8081");
        TagVO tagVO = new TagVO();
        tagVO.setName("IS_ADMIN");
        userTagClient.addTag("4114c4eb73ac425e9c1966c4eb473cac", "wednesday", tagVO);
    }
}