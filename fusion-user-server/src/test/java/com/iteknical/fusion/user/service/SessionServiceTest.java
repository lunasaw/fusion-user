package com.iteknical.fusion.user.service;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    // @Test
    public void testCleanExpired() {
        // sessionService.cleanExpired();
    }
}
