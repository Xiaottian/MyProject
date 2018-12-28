/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.util
 * FileName：      Bucket.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/26 14:31
 */

package com.jit.demo.util;

import java.util.ArrayList;
import java.util.List;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.BucketSummary;
import com.baidubce.services.bos.model.CannedAccessControlList;
import com.baidubce.services.bos.model.Grant;
import com.baidubce.services.bos.model.Grantee;
import com.baidubce.services.bos.model.Permission;

public class Bucket
{
	/**
	 * 创建bucket
	 *
	 * @param client
	 * @param bucketName
	 */
	public void createBucket(BosClient client, String bucketName)
	{
		// 新建一个Bucket
		client.createBucket(bucketName);
	}

	/**
	 * 获取bucket列表
	 *
	 * @param client
	 */
	public void listBuckets(BosClient client)
	{
		// 获取用户的Bucket列表
		List<BucketSummary> buckets = client.listBuckets().getBuckets();

		// 遍历Bucket
		for (BucketSummary bucket : buckets)
		{
			System.out.println(bucket.getName());
		}
	}

	/**
	 * 判断Bucket是否存在
	 *
	 * @param client
	 * @param bucketName
	 */
	public boolean doesBucketExist(BosClient client, String bucketName)
	{

		// 获取Bucket的存在信息
		boolean exists = client.doesBucketExist(bucketName);

		// 输出结果
		if (exists)
		{
			System.out.println("Bucket exists");
		} else
		{
			System.out.println("Bucket not exists");
		}
		return exists;
	}

	/**
	 * 删除Bucket
	 *
	 * @param client
	 * @param bucketName
	 */
	public void deleteBucket(BosClient client, String bucketName)
	{
		// 删除Bucket
		client.deleteBucket(bucketName);
	}

	/**
	 * 设置Bucket的访问权限
	 *
	 * @param client
	 * @param bucketName
	 */
	public void setBucketPrivate(BosClient client, String bucketName)
	{
		client.setBucketAcl(bucketName, CannedAccessControlList.Private);
	}

	/**
	 * 设置指定用户对Bucket的访问权限
	 *
	 * @param client
	 */
	public void SetBucketAclFromBody(BosClient client)
	{
		List<Grant> grants = new ArrayList<Grant>();
		List<Grantee> grantee = new ArrayList<Grantee>();
		List<Permission> permission = new ArrayList<Permission>();

		// 授权给特定用户
		grantee.add(new Grantee("UserId_1"));
		grantee.add(new Grantee("UserId_2"));
		// 授权给Everyone
		grantee.add(new Grantee("*"));

		// 设置权限
		//Permission中的权限设置包含三个值：READ、WRITE、FULL_CONTROL
		permission.add(Permission.READ);
		permission.add(Permission.WRITE);

		grants.add(new Grant().withGrantee(grantee).withPermission(permission));
		//client.setBucketAcl("bucketName", grants);
	}
}
