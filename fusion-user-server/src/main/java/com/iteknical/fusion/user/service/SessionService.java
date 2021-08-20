package com.iteknical.fusion.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iteknical.common.utils.HashUtils;
import com.iteknical.fusion.user.constant.ConstantHolder;
import com.iteknical.fusion.user.dao.SessionDAO;
import com.iteknical.fusion.user.entity.SessionDO;

/**
 * @author Luna
 */
@EnableScheduling
@Service
public class SessionService {
    private final static Logger logger = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    private SessionDAO          sessionDAO;

    public String add(long userId) {
        String key = HashUtils.randomHex32();

        SessionDO sessionDO = new SessionDO();
        sessionDO.setKey(key);
        sessionDO.setUserId(userId);
        sessionDAO.insert(sessionDO);

        return key;
    }

    /**
     * session key换userId
     * 
     * @param key
     * @return
     */
    public Long get(String key) {
        SessionDO sessionDO = sessionDAO.get(key);
        if (sessionDO == null) {
            return null;
        }

        sessionDAO.refresh(sessionDO);

        return sessionDO.getUserId();
    }

    /**
     * 清除过期session
     * <p>
     * 每次任务执行完之后的6小时再次运行
     * <p/>
     */
    @Scheduled(fixedDelay = 1000 * 60 * 60 * ConstantHolder.SESSION_EXPIRED_HOUR)
    private void cleanExpired() {
        logger.info("start cleanExpired");
        List<SessionDO> sessionDOList = sessionDAO.listExpiredSession(ConstantHolder.SESSION_EXPIRED_HOUR);
        sessionDOList.forEach(sessionDO -> {
            sessionDAO.delete(sessionDO.getKey());
        });
        logger.info("cleanExpired finish, size={}", sessionDOList.size());
    }
}
