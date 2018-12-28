/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc
 * FileName：      ThreadSellTickets.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/24 10:56
 */

package com.slasher.juc;

import java.lang.invoke.LambdaConversionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程卖票
 * 3个售票员(线程)        卖出(资源类方法)      30 张票
 * 1. 线程 操作 资源类
 * 2. 高内聚   低耦合
 * 结论：资源类自身以高内聚的方式，自身携带同步方法，对外暴露给多线程调用
 */
public class ThreadSellTickets {
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 0; i < 40; i++) {ticket.sale();}},"AA").start();
        new Thread(() -> {for (int i = 0; i < 40; i++) {ticket.sale(); }},"BB").start();
        new Thread(() -> {for (int i = 0; i < 40; i++) { ticket.sale();}},"CC").start();

        /*new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();*/
    }
}

class Ticket{
    private int number = 30;
    private Lock lock = new ReentrantLock(); //List list = new ArrayList();
    public void sale(){
        lock.lock();
        try {
            if (number >0 )
                System.out.println(Thread.currentThread().getName() + "\t卖出第，" + (number--) + "张票" + "\t还剩下，" + (number) + "张票");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}