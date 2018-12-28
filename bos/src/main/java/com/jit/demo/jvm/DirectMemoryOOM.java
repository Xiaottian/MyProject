/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.jvm
 * FileName：      DirectMemoryOOM.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/20 17:26
 */

package com.jit.demo.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        //通过反射获取Unsafe实例进行内存分配(Unsafe类的getUnsafe()方法限制了只有引导类加载器才会返回实例)
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
