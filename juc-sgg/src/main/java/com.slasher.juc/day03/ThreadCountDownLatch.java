/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      ThreadCountDownLatch.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/28 14:53
 */

package com.slasher.juc.day03;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * 当一个或多个线程调用await 方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1（调用countDown方法的线程不会阻塞）
 * 当计数器的值变为0 时，因await 方法阻塞的线程会被唤醒，继续执行
 *
 * 解释：
 */
public class ThreadCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t国家被灭");
                latch.countDown();
            },CountryEnums.foreachCountryEnums(i).getRetMessage()).start();
        }
        latch.await();  //什么时候变为零了，main线程才继续运行
        System.out.println(Thread.currentThread().getName() + "**********秦国统一6国");
    }
}
