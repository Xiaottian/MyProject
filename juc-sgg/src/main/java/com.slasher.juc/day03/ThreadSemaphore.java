/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      ThreadSeamphore.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/28 16:33
 */

package com.slasher.juc.day03;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 在信号量上定义两种操作，
 * acquire（获取）当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去
 * 直到有线程释放信号量，或超时
 * release（释放）实际上会将信号量的值+1 ，然后唤醒等待的线程
 *
 * 信号量主要用于两个目的：一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制
 */
public class ThreadSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t占据车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));//每个车停0-5s之间
                    System.out.println(Thread.currentThread().getName() + "\t********离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
