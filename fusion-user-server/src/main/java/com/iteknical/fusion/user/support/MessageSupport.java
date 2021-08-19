package com.iteknical.fusion.user.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.utils.ResultDTOUtils;
import com.iteknical.fusion.message.client.MessageClient;
import com.iteknical.fusion.message.dto.MessageDTO;

/**
 * @author Iszychen@win10
 * @date 2020/2/5 21:44
 */
@Component
public class MessageSupport {

    private Logger        logger = LoggerFactory.getLogger(MessageSupport.class);

    @Autowired
    private MessageClient messageClient;

    public void asyncSendMessage(MessageDTO messageDTO) {
        ResultDTO<Void> resultDTO = messageClient.asyncSendMessage(messageDTO);
        logger.info("messageClient.asyncSendMessage, messageDTO={}, ", messageDTO,
            JSON.toJSONString(resultDTO));
        ResultDTOUtils.checkResultAndGetData(resultDTO);
    }
}
