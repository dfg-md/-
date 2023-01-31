package com.zzs.guli.vod.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-07 15:39
 **/
@Component
public class ConstantVodUtils implements InitializingBean {


    @Value("${aliyun.vod.file.keyid}")
    private  String kid;

    @Value("${aliyun.vod.file.keysecret}")
    private  String keySecret;

    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_KEY_ID;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_SECRET = keySecret;
        ACCESS_KEY_ID=kid;
    }
}
