package com.zzs.guli;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2023-01-17 19:30
 **/
public class StreamDemo {


    /**
     * 创建一个数组 找出姓名是张的 放入新的集合中
     */
    @Test
    public void Streamdemo1(){
        // 已知的知识来解决需求
        List<String> list1 = new ArrayList<>();
        list1.add("张老三");
        list1.add("张小三");
        list1.add("李逍遥");
        list1.add("李明");
        list1.add("张三丰");
        list1.add("赵敏");

//        name.stream().filter(s -> s.startsWith("张")).forEach(
//                System.out.println(s)
//        );

//        list1.stream().filter(s->s.startsWith("李")).forEach(s->{
//            System.out.println("符合条件的姓名：" + s);
//        });

        list1.stream().filter(s->s.startsWith("李")).map(s ->"1 符合条件的姓名：" + s).
                forEach(System.out::println);

        List<String> list2 = new ArrayList<>();
        list1.stream().map(a -> new student(a)).forEach(System.out::println);
    }


}
