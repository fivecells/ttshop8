package com.qf.ttshop.sso.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * User: DHC
 * Date: 2018/2/5
 * Time: 11:53
 * Version:V1.0
 */
public class RedisUtils {
    private static JedisPool pool;
    static {
        pool=new JedisPool("101.132.38.253",6379);

    }
    public static void set(String key,String value,int time){
        Jedis jedis=pool.getResource();
        jedis.set(key,value);
        jedis.expire(key,time);
        jedis.close();
    }
    public  static void setTime(String key,int time){
        Jedis jedis=pool.getResource();
        jedis.expire(key,time);
        jedis.close();
    }
    public static String get(String key){
        Jedis jedis=pool.getResource();
        String r=jedis.get(key);
        jedis.close();
        return  r;
    }
}
