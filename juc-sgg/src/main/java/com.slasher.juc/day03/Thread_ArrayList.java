/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      Thread_ArrayList.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/28 17:05
 */

package com.slasher.juc.day03;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 多线程 + 高并发场景下，使用写实复制技术
 */
public class Thread_ArrayList {
    public static void main(String[] args) {
        //Map<String,String> map = new HashMap<>();//线程不安全的
        Map<String,String> map = new ConcurrentHashMap<>(); //线程安全的
        for (int i = 0; i < 55; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 9));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }


    }

    private static void SetNotSafeToSafe() {
        //Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 55; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 9));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void ListNotSafeToSafe() {
        //        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();

        //多线程 + 高并发 情境下，如下会出错
        // Exception in thread "3" java.util.ConcurrentModificationException并发修改错误
        for (int i = 0; i < 55; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 9));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
