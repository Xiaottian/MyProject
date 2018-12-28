/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.util
 * FileName：      BosClient.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/26 10:39
 */

package com.jit.demo.util;

import java.io.IOException;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

/**
 * 连接到百度云bos
 */
public class MyBosClient {
	public static void main(String[] args) {
		String ACCESS_KEY_ID = "dfc7eb348c074da2bb71a554209d2ef2";                   // 用户的Access Key ID
		String SECRET_ACCESS_KEY = "fa98536d196d45d08a887d9bead3198c";           // 用户的Secret Access Key

		// 初始化一个BosClient
		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));

		/**
		 * 注意：ENDPOINT参数只能用指定的包含Region的域名来进行定义，目前BOS只提供北京一个Region，因此ENDPOINT支持主域名http://bj.bcebos.com和备域名http://bj.baidubos.com，随着Region的增加将会开放其他可以支持的域名。
		 */
		String ENDPOINT = "http://bj.bcebos.com";
		config.setEndpoint(ENDPOINT);

		BosClient client = new BosClient(config);

		Bucket bucket = new Bucket();

		/**
		 * Bucket感觉就是数据库表（或者说是存储区域），而Object就是数据，跟非关系数据库的存储方式很像，
		 * 但BOS是一个开放的存储对象平台或者是公用数据库存储集群
		 */
		String bucketName = "jit-cipsc";

		if (!bucket.doesBucketExist(client, bucketName)) {
			bucket.createBucket(client, bucketName);
		}


		Object obj = new Object();

		obj.putObject(client, bucketName, "boonyakey", "hello,bos!");

		try {
			obj.getObject(client, bucketName, "boonyakey");
		} catch (IOException e) {
			System.out.println("BOS获取对象，异常：" + e.getMessage());
		}
	}
}


