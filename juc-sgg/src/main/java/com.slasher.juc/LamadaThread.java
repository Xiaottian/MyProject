/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc
 * FileName：      LamadaThread.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/24 11:48
 */

package com.slasher.juc;

/**
 * 面向接口编程
 * lambda表达式
 */
public class LamadaThread {
    public static void main(String[] args) {
//        Foo foo = () -> System.out.println("***** 886 2018 !");
//        foo.say886();
        Foo foo = (x,y) -> {return x + y;};
        int result = foo.div(10,2);
        System.out.println("********result " + result);
        System.out.println(foo.add(2, 3));
        System.out.println(Foo.div2(15,3));
    }
}

//接口中只有一个方法，这个接口叫做函数式接口
@FunctionalInterface
interface Foo{
//    public void say886();
    public int add(int x,int y);

    default int div(int x,int y){
        return x/y;
    }

    public static int div2(int x,int y){
        return x/y;
    }
}
