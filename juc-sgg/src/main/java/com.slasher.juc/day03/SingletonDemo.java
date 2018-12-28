/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      SingleDemo.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/28 17:39
 */

package com.slasher.juc.day03;

public class SingletonDemo {
    private static SingletonDemo instance = null;

    public SingletonDemo() {
        System.out.println("**********" + Thread.currentThread().getName());
    }

    public static SingletonDemo getInstance(){
        if (null == instance){
            instance = new SingletonDemo();
        }
        return instance;
    }
    public static void main(String[] args) {
        getInstance();
    }
}
