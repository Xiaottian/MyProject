/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.jvm
 * FileName：      RuntimeConstantPoolOOM.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/20 16:55
 */

package com.jit.demo.jvm;

public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1);
        System.out.println(str1.intern() == str1); //true

        System.out.println("--------------------------------");

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern() == str2); //false，因为"java"在常量池已经存在

        System.out.println("--------------------------------");

        String str3 = new StringBuilder("田").append("杰").toString();
        System.out.println(str3);
        System.out.println(str3.intern() == str3); //true

        System.out.println("--------------------------------");

        String str4 = new StringBuilder("cla").append("ss").toString();
        System.out.println(str4);
        System.out.println(str4.intern() == str4);

    }
}
