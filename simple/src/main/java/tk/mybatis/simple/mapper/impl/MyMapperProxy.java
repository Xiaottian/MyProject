/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.mapper.impl
 * FileName：      MyMapperProxy.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/13 18:05
 */

package tk.mybatis.simple.mapper.impl;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyMapperProxy<T> implements InvocationHandler {
	private Class<T> mapperInterface;
	private SqlSession sqlSession;

	public MyMapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
		this.mapperInterface = mapperInterface;
		this.sqlSession = sqlSession;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//针对不同的sql类型，需要调用SqlSession不同的方法
		//接口方法中的参数也有很多情况，这里只考虑没有参数的情况
		List<T> list = sqlSession.selectList(mapperInterface.getCanonicalName() + "." + method.getName());
		//返回值有很多情况，这里不做处理直接返回
		return list;
	}
}
