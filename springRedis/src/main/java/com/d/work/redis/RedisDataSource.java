package com.d.work.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	
	/**
	 * 
	* @Title: getShardedJedis 
	* @Description: 取得redis客户端
	* @param @return    设定文件 
	* @return ShardedJedis    返回类型 
	* @throws
	 */
	public abstract ShardedJedis getShardedJedis();
	
	/**
	 * 
	* @Title: returnResource 
	* @Description: 将资源返还给pool
	* @param @param shardedJedis    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void returnResource(ShardedJedis shardedJedis);
	
	/**
	 * 
	* @Title: returnResource 
	* @Description: 出现异常后将资源返还给pool
	* @param @param shardedJedis
	* @param @param broken    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void returnResource(ShardedJedis shardedJedis,boolean broken);
	
}
