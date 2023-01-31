package com.zzs.guli;

import lombok.Data;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2023-01-17 19:53
 **/

public class student {

   private String name;
//   private int age;


   public student(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "student{" +
              "name='" + name + '\'' +
              '}';
   }
}
