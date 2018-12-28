/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.jvm
 * FileName：      ReferenceCountingGC.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/20 17:45
 */

package com.jit.demo.jvm;

/**
 * 引用技术算法的缺陷
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便在GC日志中看是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        //在这发生GC，objA和objB是否被回收
        System.gc();
    }

}
