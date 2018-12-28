/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      ThreadReadWrite.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/24 17:36
 */

package com.slasher.juc.day03;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 * 一个线程写，100个线程读
 */
public class ThreadReadWrite {
    public static void main(String[] args) throws InterruptedException {
        ResourceData resourceData = new ResourceData();
        Thread.sleep(500);
        new Thread(() -> {
            resourceData.writeObj("hello,MyWrite");
        },"writeThread").start();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                resourceData.readObj();
            },String.valueOf(i)).start();
        }
    }
}
class ResourceData{
    private Object object;
    private ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();

    public void writeObj(Object object){
        rrwLock.writeLock().lock();
        try {
            this.object = object;
            System.out.println(Thread.currentThread().getName() + "\t writeObj :" + object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rrwLock.writeLock().unlock();
        }
    }

    public void readObj(){
        rrwLock.writeLock().lock();
        try {
            this.object = object;
            System.out.println(Thread.currentThread().getName() + "\t readObj :" + object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rrwLock.writeLock().unlock();
        }
    }
}
