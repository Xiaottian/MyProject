/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      ThreadSEQInvoking.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/24 17:11
 */

package com.slasher.juc.day03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间按顺序调用，实现A -> B -> C
 * 三个线程启动，要求如下
 * AA 打印5次， BB 打印10次， CC 打印15次
 * 接着
 * AA 打印5次， BB 打印10次， CC 打印15次
 * 。。。
 * 共计20轮
 */
public class ThreadSEQInvoking {
    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
               data.print5(i);
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                data.print10(i);
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                data.print15(i);
            }
        },"C").start();
    }
}

class ShareData{
    private int number = 1; //1:A   2:B   3:C
    private Lock lock = new ReentrantLock();
    //一定要注意用三个condition，因为要单个儿指定的去完成通知
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int totalLoop){
        lock.lock();
        try {
            // 1 判断
            while (number != 1){
                c1.await();
            }
            // 2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i + "\ttotalLoop:" + totalLoop);
            }
            // 3 唤醒
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10(int totalLoop){
        lock.lock();
        try {
            // 1 判断
            while (number != 2){
                c2.await();
            }
            // 2 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i + "\ttotalLoop:" + totalLoop);
            }
            // 3 唤醒
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print15(int totalLoop){
        lock.lock();
        try {
            // 1 判断
            while (number != 3){
                c3.await();
            }
            // 2 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i + "\ttotalLoop:" + totalLoop);
            }
            // 3 唤醒
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
