package com.zzs.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: StarSea99
 * @Date: 2020-10-09 15:44
 */
public class TestEasyExcel {
    public static void main(String[] args) {

        //路径操作
//        String fileName =  "D:\\study\\JavaProject\\guli_parent-master\\students.xlsx";
//        //写操作

//        EasyExcel.write(fileName,DemoData.class)
//                .sheet("学生列表")
//                .doWrite(getData());

        //创建方法返回list 集合

        //路径操作
        String fileName =  "D:\\study\\JavaProject\\guli_parent-master\\students.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener())
                .sheet()
                .doRead();
//        //实现excel读的操作
//        String filename = "E:\\write.xlsx";
//        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
//    }
//
//    //创建方法返回list集合
//    private static List<DemoData> getData(){
//        List<DemoData> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            DemoData data = new DemoData();
//            data.setSno(i);
//            data.setSname("lucy"+i);
//            list.add(data);
//        }
//        return list;
//    }
}
    private static  List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData date = new DemoData();
            date.setSno(i);
            date.setSname("lucy"+i);
            list.add(date);
        }
        return list;
    }
}