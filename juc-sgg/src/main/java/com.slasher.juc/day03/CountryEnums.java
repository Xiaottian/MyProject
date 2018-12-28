/**
 * ProjectName:    MyProject
 * PackageName:    com.slasher.juc.day03
 * FileName：      CountryEnums.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/28 16:13
 */

package com.slasher.juc.day03;

public enum CountryEnums {
    ONE(1,"韩"),TWO(2,"魏"),THREE(3,"赵"),FOUR(4,"齐"),FIVE(5,"楚"),SIX(6,"燕");

    private Integer retCode;
    private String retMessage;

    CountryEnums(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public static CountryEnums foreachCountryEnums(Integer index){
        for (CountryEnums countryEnums : values()) {
            if (countryEnums.getRetCode() == index){
                return countryEnums;
            }
        }
        return null;
    }
}
