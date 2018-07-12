package com.sl.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache implements Cache
{
	
    private Jedis redisClient = createClient();
    
    
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
 
    private String id;
 
    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        
        this.id = id;
    }
 
    public String getId() {
        return this.id;
    }
 
    public int getSize() {
        return Integer.valueOf(redisClient.dbSize().toString());
    }
 
    public void putObject(Object key, Object value) {
    	
        redisClient.set(SerializeHelper.serialize(key.toString()), SerializeHelper.serialize(value));
    }
 
    public Object getObject(Object key) {
        Object value = SerializeHelper.unserialize(redisClient.get(SerializeHelper.serialize(key.toString())));
        return value;
    }
 
    public Object removeObject(Object key) {
        return redisClient.expire(SerializeHelper.serialize(key.toString()), 0);
    }
 
    public void clear() {
    	
        redisClient.flushDB();
    }
 
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
 
    protected static Jedis createClient() {
        try {
            JedisPool pool = new JedisPool(new JedisPoolConfig(),"localhost");
            return pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("初始化连接池错误");
    }
    

}
