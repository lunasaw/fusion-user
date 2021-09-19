package com.luna.fusion.user.bean;

import com.luna.fusion.message.client.MessageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Iszychen@win10
 * @date 2020/2/5 21:44
 */
@Configuration
public class MessageClientBean {
    @Value("${fusion.message.host}")
    private String messageClientHost;

    @Bean
    public MessageClient messageClient() {
        return new MessageClient(messageClientHost);
    }
}
