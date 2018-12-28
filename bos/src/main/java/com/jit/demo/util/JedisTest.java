/**
 * ProjectName:    MyProject
 * PackageName:    com.jit.demo.util
 * FileName：      JedisTest.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/12/12 15:47
 */

package com.jit.demo.util;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("106.13.36.39",6379);
        String s = jedis.ping();
        jedis.set("k1","v1");
        System.out.println(jedis.get("k1"));
        Set<String> keys = jedis.keys("*");
        List<String> l1 = jedis.lrange("l1", 0, -1);
        Set<String> s1 = jedis.smembers("s1");
        Map map = new HashMap();
        map.put("h1","v1");
        map.put("h2","v2");
        map.put("h3","v3");
        jedis.hmset("hash对应java的map类型",map);
        Set<String> hkeys = jedis.hkeys("hash对应java的map类型");
        System.out.println("hkeys的size是 " + hkeys.size());
        List<String> hvals = jedis.hvals("hash对应java的map类型");
        List<String> list = jedis.hmget("hash对应java的map类型", "h1", "h2");
        System.out.println("----------------------**************-------------------");
        for (String s2 : list) {
            System.out.println("jedis.hmget方法取到的值为 " + s2);
        }
        System.out.println("hvals的size是 " + hvals.size());
        System.out.println(s);
        jedis.close();
    }
}
