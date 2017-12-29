package com.d.work.redis.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.d.work.redis.RedisDataSource;

public class RedisDataSoueceImpl implements RedisDataSource {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisDataSoueceImpl.class);
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	/**
	 *获取redis客户端
	 */
	public ShardedJedis getShardedJedis() {
		try {
			ShardedJedis shardedJedis = shardedJedisPool.getResource();
			return shardedJedis;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将资源返还给pool
	 */
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnBrokenResource(shardedJedis);
	}

	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if (broken) {
			shardedJedisPool.returnBrokenResource(shardedJedis);
		} else {
			shardedJedisPool.returnResource(shardedJedis);
		}
	}

}
