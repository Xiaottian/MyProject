/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.util
 * FileName：      Object.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/26 14:32
 */

package com.jit.demo.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.CopyObjectRequest;
import com.baidubce.services.bos.model.CopyObjectResponse;
import com.baidubce.services.bos.model.GetObjectRequest;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectResponse;

public class Object
{
	/**
	 * BOS Java SDK本质上是调用后台的HTTP接口，因此BOS服务允许用户自定义Object的Http Header
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param content
	 */
	public void setHttpHeader(BosClient client, String bucketName, String objectKey, String content)
	{
		// 初始化上传输入流
		ObjectMetadata meta = new ObjectMetadata();

		// 设置ContentLength大小
		meta.setContentLength(1000);

		// 设置ContentType
		meta.setContentType("application/json");

		client.putObject(bucketName, objectKey, content, meta);
	}

	/**
	 * 用户自定义元数据
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param content
	 */
	public void selfDefinitedObject(BosClient client, String bucketName, String objectKey, String content)
	{
		// 初始化上传输入流
		ObjectMetadata meta = new ObjectMetadata();

		// 设置ContentLength大小
		meta.setContentLength(1000);
		// 设置自定义元数据name的值为my-data
		meta.addUserMetadata("name", "my-data");

		// 上传Object
		client.putObject(bucketName, objectKey, content, meta);
	}

	/**
	 * 添加对象到Bucket
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param content
	 */
	public void putObject(BosClient client, String bucketName, String objectKey, String content)
	{
		// 以字符串上传Object
		PutObjectResponse putObjectResponseFromString = client.putObject(bucketName, objectKey, content);

		// 打印ETag
		System.out.println(putObjectResponseFromString.getETag());
	}

	/**
	 * BOS一共支持四种形式的Object上传，参考如下代码
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param byte1
	 * @param string1
	 * @throws FileNotFoundException
	 */
	public void PutObject(BosClient client, String bucketName, String objectKey, byte[] byte1, String string1) throws FileNotFoundException
	{
		// 获取指定文件
		File file = new File("/path/to/file.zip");
		// 获取数据流
		InputStream inputStream = new FileInputStream("/path/to/test.zip");

		// 以文件形式上传Object
		PutObjectResponse putObjectFromFileResponse = client.putObject(bucketName, objectKey, file);
		// 以数据流形式上传Object
		PutObjectResponse putObjectResponseFromInputStream = client.putObject(bucketName, objectKey, inputStream);
		// 以二进制串上传Object
		PutObjectResponse putObjectResponseFromByte = client.putObject(bucketName, objectKey, byte1);
		// 以字符串上传Object
		PutObjectResponse putObjectResponseFromString = client.putObject(bucketName, objectKey, string1);

		// 打印ETag
		System.out.println(putObjectFromFileResponse.getETag());
	}

	/**
	 * 列出Bucket中的Object
	 *
	 * @param client
	 * @param bucketName
	 */
	public void listObjects(BosClient client, String bucketName)
	{

		// 获取指定Bucket下的所有Object信息
		ListObjectsResponse listing = client.listObjects(bucketName);

		// 遍历所有Object
		for (BosObjectSummary objectSummary : listing.getContents())
		{
			System.out.println("ObjectKey: " + objectSummary.getKey());
		}

	}

	/**
	 * 简单的读取Object
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @throws IOException
	 */
	public void getObject(BosClient client, String bucketName, String objectKey) throws IOException
	{

		// 获取Object，返回结果为BosObject对象
		BosObject object = client.getObject(bucketName, objectKey);

		// 获取ObjectMeta
		ObjectMetadata meta = object.getObjectMetadata();

		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();

		// 处理Object
		// ...

		// 关闭流
		objectContent.close();
	}

	/**
	 * 简单的读取Object
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @throws IOException
	 */
	public void getObject2(BosClient client, String bucketName, String objectKey)
	{
		// 新建GetObjectRequest
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectKey);

		// 获取0~100字节范围内的数据
		getObjectRequest.setRange(0, 100);

		// 获取Object，返回结果为BosObject对象
		BosObject object = client.getObject(getObjectRequest);

	}

	/**
	 * 下载Object到文件
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 */
	public void downloadObjectToFile(BosClient client, String bucketName, String objectKey)
	{
		// 新建GetObjectRequest
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectKey);

		// 下载Object到文件
		ObjectMetadata objectMetadata = client.getObject(getObjectRequest, new File("/path/to/file"));
	}

	/**
	 * 只获取ObjectMetadata
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 */
	public ObjectMetadata getObjectMetadata(BosClient client, String bucketName, String objectKey)
	{
		ObjectMetadata objectMetadata = client.getObjectMetadata(bucketName, objectKey);
		return objectMetadata;
	}

	/**
	 * 获取下载Object的URL
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param expirationInSeconds
	 * @return
	 */
	public String generatePresignedUrl(BosClient client, String bucketName, String objectKey, int expirationInSeconds)
	{

		URL url = client.generatePresignedUrl(bucketName, objectKey, expirationInSeconds);
		return url.toString();
	}

	/**
	 * 删除Object
	 *
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 */
	public void deleteObject(BosClient client, String bucketName, String objectKey)
	{

		// 删除Object
		client.deleteObject(bucketName, objectKey);
	}

	/**
	 * 拷贝Object
	 *
	 * @param client
	 * @param srcBucketName
	 * @param srcKey
	 * @param destBucketName
	 * @param destKey
	 */
	public void copyObject(BosClient client, String srcBucketName, String srcKey, String destBucketName, String destKey)
	{

		// 拷贝Object
		CopyObjectResponse copyObjectResponse = client.copyObject(srcBucketName, srcKey, destBucketName, destKey);

		// 打印结果
		System.out.println("ETag: " + copyObjectResponse.getETag() + " LastModified: " + copyObjectResponse.getLastModified());
	}

	/**
	 * 拷贝Object
	 *
	 * @param client
	 * @param srcBucketName
	 * @param srcKey
	 * @param destBucketName
	 * @param destKey
	 */
	public void copyObject2(BosClient client, String srcBucketName, String srcKey, String destBucketName, String destKey)
	{
		// 创建CopyObjectRequest对象
		CopyObjectRequest copyObjectRequest = new CopyObjectRequest(srcBucketName, srcKey, destBucketName, destKey);

		// 设置新的Metadata
		Map<String, String> userMetadata = new HashMap<String, String>();

		userMetadata.put("<user-meta-key>", "<user-meta-value>");

		ObjectMetadata meta = getObjectMetadata(client, destBucketName, destKey);

		meta.setUserMetadata(userMetadata);

		copyObjectRequest.setNewObjectMetadata(meta);

		// 复制Object
		CopyObjectResponse copyObjectResponse = client.copyObject(copyObjectRequest);

		System.out.println("ETag: " + copyObjectResponse.getETag() + " LastModified: " + copyObjectResponse.getLastModified());
	}
}

