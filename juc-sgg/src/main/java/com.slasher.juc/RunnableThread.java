/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc
 * FileName：      RunnableThread.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/24 15:06
 */

package com.slasher.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 在线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
 * 当主线程将来需要时，可以通过Future对象获得后台作业的计算结果或者执行状态。
 * 仅在计算完成时才能检索结果。如果计算尚未完成，则阻塞get方法，否则会一直阻塞
 */
public class RunnableThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft = new FutureTask<>(new MyThread());
        new Thread(ft,"AA").start();
        new Thread(ft,"BB").start();
        Integer integer1 = ft.get();
        System.out.println("*********main()***** result1 " + integer1);
        Integer integer2 = ft.get();
        System.out.println("*********main()***** result2 " + integer2);
    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("******** call() *******");
        return 200;
    }
}
