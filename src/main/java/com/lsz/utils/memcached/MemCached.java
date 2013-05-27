package com.lsz.utils.memcached;

import java.util.Date;
import com.lsz.utils.config.CenterProp;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemCached
{
    // 创建全局的唯一实例
    protected static MemCachedClient mcc = new MemCachedClient();
    protected static MemCached memCached = new MemCached();
    
    // 设置与缓存服务器的连接池
    static {
        // 服务器列表和其权重
        String[] servers = {CenterProp.memcacheServer};
        Integer[] weights = {3};

        // 获取socke连接池的实例对象
        SockIOPool pool = SockIOPool.getInstance();

        // 设置服务器信息
        pool.setServers( servers );
        pool.setWeights( weights );

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn( CenterProp.memcacheInitConn);
        pool.setMinConn(CenterProp.memcacheMinConn);
        pool.setMaxConn(CenterProp.memcacheMaxConn);
        pool.setMaxIdle(CenterProp.memcacheMaxIdle);

        // 设置主线程的睡眠时间
        pool.setMaintSleep(CenterProp.memcacheMaintSleep);

        // 设置TCP的参数，连接超时等
        pool.setNagle( CenterProp.memcacheNagle );
        pool.setSocketTO(CenterProp.memcacheSocketTO );
        pool.setSocketConnectTO(CenterProp.memcacheSocketConnectTO);

        // 初始化连接池
        pool.initialize();

        // 压缩设置，超过指定大小（单位为K）的数据都会被压缩
        mcc.setCompressEnable( true );
        mcc.setCompressThreshold( 64 * 1024 );
        
        
    }
    
    /**
     * 保护型构造方法，不允许实例化！
     *
     */
    protected MemCached()
    { 
    }
    
    /**
     * 获取唯一实例.
     * @return
     */
    public static MemCached getInstance()
    {
        return memCached;
    }
    
    /**
     * 添加一个指定的值到缓存中.
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value)
    {
        return mcc.add(key, value);
    }
    
    public boolean add(String key, Object value, Date expiry)
    {
        return mcc.add(key, value, expiry);
    }
    
    public boolean replace(String key, Object value)
    {
        return mcc.replace(key, value);
    }
    
    public boolean replace(String key, Object value, Date expiry)
    {
        return mcc.replace(key, value, expiry);
    }
    
    public boolean set(String key, Object value)
    {
        return mcc.set(key, value);
    }
    
    public boolean set(String key, Object value, Date expiry)
    {
        return mcc.set(key, value, expiry);
    }
    
    /**
     * 根据指定的关键字获取对象.
     * @param key
     * @return
     */
    public Object get(String key)
    {	
        return mcc.get(key);
    }   
}