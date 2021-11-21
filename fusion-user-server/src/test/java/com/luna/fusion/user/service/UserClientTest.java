package com.luna.fusion.user.service;

import com.luna.fusion.user.client.UserClient;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenzhangyue@weidian.com
 * 2021/9/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserClientTest {

    private UserClient userClient = new UserClient("http://localhost:8001");

    @Test
    public void atest() {
        userClient.getUserIdBySessionKey("xxx", "wednesday");
    }
}
