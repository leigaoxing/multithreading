package com.itcast.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 面试题，设计一个缓存系统
 * @author adminitrator
 *
 */
public class CacheDemo {
	private Map<String, Object> cache = new HashMap<>();
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	/**
	 * 如果要查对象，先找cache，cache中有，直接返回，如果没有，数据库中查
	 * 之后返回
	 * @param key
	 * @return
	 */
	public Object getData(String key){
		rwl.readLock().lock();
		Object value;
		try {
			value = cache.get(key);
			if(value == null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if(value == null )//此判断是如果多个线程执行到读锁解锁后开始写锁加锁，在第一个赋值完成后，以后的线程只需要读这个值就好了，不用重复赋值。可以看下ReentrantReadWriteLock注释的例子
						value = "aaa"; //实际是去queryDB()查询数据库
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
