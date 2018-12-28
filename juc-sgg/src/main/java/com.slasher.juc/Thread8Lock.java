/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc
 * FileName：      Thread8Lock.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/24 16:39
 */

package com.slasher.juc;

import java.util.concurrent.TimeUnit;

public class Thread8Lock {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.getIOS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(100);
        new Thread(() -> {
            try {
                //phone.getAndroid();
                phone.getHello();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}

class Phone{
    public synchronized void getIOS() throws InterruptedException{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*******getIOS");
    }
    public synchronized void getAndroid() throws InterruptedException{
        System.out.println("*******getAndroid");
    }
    public void getHello() throws InterruptedException{
        System.out.println("*******getHello");
    }
}
