package com.jw.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author jw
 * @desc
 */
@Configuration
public class BasicUtil {
    private static final Logger logger = LoggerFactory.getLogger(BasicUtil.class);
    private static BasicUtil basicUtil = null;

    //获取实例对象
    public static BasicUtil getKeysInstant() {
        if (basicUtil == null) {
            basicUtil = AppContext.getBean(BasicUtil.class);
        }
        return basicUtil;
    }

    /**
     * 主键生成策略
     *
     * @param tableName 需要获取主键的表名
     */
    public String getSequence(String tableName) throws GeneralException {
        String id = null;
        try {
            //生成16位随机数
            id = System.currentTimeMillis()+String.format("%03d",(int)(Math.random()*100));
        } catch (Exception e) {
            logger.error("生成主键异常",e);
            throw new GeneralException(e.getMessage());
        }
        return id;
    }
}