package com.d.work.redis;

import java.util.concurrent.BrokenBarrierException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;


@Repository("redisClientTemplate")
public class ResdisCilentTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(ResdisCilentTemplate.class);
	@Autowired
	private RedisDataSource redisDataSource;
	
	public void disconnect(){
		ShardedJedis shardedJedis = redisDataSource.getShardedJedis();
		shardedJedis.disconnect();
	}
	
	/**
	 * 
	* @Title: set 
	* @Description: 设置单个值
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String set(String key,String value){
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getShardedJedis();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
	/**
	 * 
	* @Title: get 
	* @Description: 获取单个值
	* @param @param key
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String get(String key){
		String result = "";
		ShardedJedis shardedJedis = redisDataSource.getShardedJedis();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: exists 
	* @Description: 判断key是否存在
	* @param @param key
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean exists(String key){
		boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getShardedJedis();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: type 
	* @Description: 返回key存贮值的类型
	* @param @param key
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String type(String key){
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getShardedJedis();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.type(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

}
