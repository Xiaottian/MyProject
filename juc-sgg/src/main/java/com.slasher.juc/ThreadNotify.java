/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc
 * FileName：      ThreadNotify.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/24 15:47
 */

package com.slasher.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadNotify {
    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(400);
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    /*使用synchronized + wait + notifyAll
    public synchronized void increment() throws InterruptedException {
        //1 判断
        while (number != 0){
            //释放对象锁
            this.wait();
        }
        // 2干活
        ++number;
        System.out.println(Thread.currentThread().getName() + "\t生产" + number);
        //3 唤醒
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //1 判断
        while (number == 0 ){
            this.wait();
        }
        // 2 干活
        --number;
        System.out.println(Thread.currentThread().getName() + "\t消费" + number);
        //3 唤醒
        this.notifyAll();
    }
    */

    public void increment() throws InterruptedException{
        lock.lock();
        try {
            //1 判断
            while (number != 0){
                //释放对象锁
                condition.await();
            }
            // 2干活
            ++number;
            System.out.println(Thread.currentThread().getName() + "\t生产" + number);
            //3 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number == 0 ){
                condition.await();
            }
            // 2 干活
            --number;
            System.out.println(Thread.currentThread().getName() + "\t消费" + number);
            //3 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
