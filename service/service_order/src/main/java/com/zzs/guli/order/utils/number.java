package com.zzs.guli.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-26 12:25
 **/
public class number {

    public static String getOrderNo(){
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
        String newData = s.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newData+result;
    }
}
