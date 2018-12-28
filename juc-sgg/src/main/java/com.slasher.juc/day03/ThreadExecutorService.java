/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      ExecutorService.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/28 10:32
 */

package com.slasher.juc.day03;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池，java中第4 种获取多线程的方式
 * 1. Executors.newFixedThreadPool(n)          获取n个线程的线程池，线程个数不变
 * 2. Executors.newSingleThreadExecutor();     获取单个线程的线程池
 * 3. Executors.newCachedThreadPool();         获取可变长度的线程池
 * 4. Executors.newScheduledThreadPool(5)      as
 */
public class ThreadExecutorService {
    public static void main(String[] args) {
        //ExecutorService service = Executors.newFixedThreadPool(5);
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        ExecutorService service = Executors.newCachedThreadPool();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        Future future = null;
        //threadFromPoolExec(service);
        try {
            for (int i = 0; i < 10; i++) {
                future = service.schedule(() -> {
                    System.out.print(Thread.currentThread().getName());
                    //Random类中的nextInt方法，返回一个介于[0-bound)之间的值
                    return new Random().nextInt(20); //有返回值则是实现的Callable接口，无返回值实现的Runnable接口
                }, 2, TimeUnit.SECONDS);    //TimeUnit.SECONDS
                System.out.println(" *******result： " + future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

    }

    private static void threadFromPoolExec(ExecutorService service) {
        Future future;
        try {
            for (int i = 0; i < 10; i++) {
                future = service.submit(() -> {
                    System.out.print(Thread.currentThread().getName());
                    //Random类中的nextInt方法，返回一个介于[0-bound)之间的值
                    return new Random().nextInt(20); //有返回值则是实现的Callable接口，无返回值实现的Runnable接口
                });
                System.out.println(" *******result： " + future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
