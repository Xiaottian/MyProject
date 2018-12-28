/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      ThreadClclicBarrier.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/28 16:48
 */

package com.slasher.juc.day03;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier字面意思是可循环（cyclic）使用的屏障，它要做的是让一组线程到达一个屏障（也可以叫一个同步点）
 * 时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的子线程才会继续干活
 * 线程进入屏障通过CyclicBarrier的await() 方法
 *  集齐7 颗龙珠召唤神龙
 */
public class ThreadClclicBarrier {
    private static final int NUMBER = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println(Thread.currentThread().getName() + "召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第，"+ temp +  "龙珠");
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        
    }
}
