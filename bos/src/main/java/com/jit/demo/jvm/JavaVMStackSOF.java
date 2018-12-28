/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.jvm
 * FileName：      JavaVMStackSOF.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/20 14:00
 */

package com.jit.demo.jvm;

public class JavaVMStackSOF {
    private int stacklength = 1;
    public void stackLeak(){
        stacklength ++ ;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + sof.stacklength);
            throw e;
        }
    }
}
