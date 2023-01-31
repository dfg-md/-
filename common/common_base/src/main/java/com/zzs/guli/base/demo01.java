package com.zzs.guli.base;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-11-24 20:06
 **/
public class demo01 {
    public static void main(String[] args) {
//        for(int i = 1;i<=5 ;i++) { // 2
//            for(int j = 5; j >= i ; j--){ //
//                //建立1号图形
//                System.out.print(" ");
//            }
//
//            for (int g = 1; g <= (2 * i - 1) ; g++) {
//                //建立2号图形
//                System.out.print("#");
//            }
//            System.out.println("");
//        }
        for (int i = 5; i >=1 ; i--) {
            for (int j = 5; j >= i  ; j--) {
                System.out.print(" ");
            }
            for (int u = 1; u  <= (2 * i )- 1  ; u++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
