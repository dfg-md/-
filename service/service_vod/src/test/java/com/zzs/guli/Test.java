package com.zzs.guli;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-07 13:30
 **/


public class Test {

    //填入AccessKey信息
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    @org.junit.Test
    public void filter() {
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        list.add("66");
//        String collect = list.stream().collect(Collectors.joining(","));
//        System.out.println(collect.toString());


//        filter  过滤str ->
        List<String> collect = list.stream().filter(str -> str.contains("1"))
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    /**
     * 去重方法
     */
    @org.junit.Test
    public void distinct() {

        List<String> list = new ArrayList<>();list.add("abc");
        list.add("ab");list.add("abc");list.add("ac");list.add("");list.add("aaa");
        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);

    }

//    @org.junit.Test
//    public void () {
//    }

    /**
     * map  对流中的所有元素做统一处理
     */
    @org.junit.Test
    public void map() {

        List<String> list = new ArrayList<>();list.add("abc");
        list.add("ab");list.add("abc");list.add("ac");list.add("");list.add("aaa");

        List<String> ssss = list.stream().map(s -> s.concat("ssss")).collect(Collectors.toList());
        System.out.println(ssss);


    }
}
