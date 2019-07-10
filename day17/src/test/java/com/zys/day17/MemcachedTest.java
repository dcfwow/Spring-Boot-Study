package com.zys.day17;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTest {
    @Autowired
    private MemcachedClient memcachedClient;

    /**
     * memcached.set()参数：
     * 第一个key名称；
     * 第二个expire时间(seconds),0表示永久存储(默认一个月)，超过这个时间,memcached将这个数据替换出去；
     * 第三个实际存储的数据
     * @throws Exception
     */
    @Test
    public void testGetAndSet() throws Exception {
        memcachedClient.set("hello",0,"Hello,xmemcached");
        String value = memcachedClient.get("hello");
        System.out.println("hello:"+value);
        memcachedClient.delete("hello");
    }

    @Test
    public void testMore() throws Exception{
        if(!memcachedClient.set("hello",0,"world")){
            System.out.println("set error");
        }
        //add用于将value存储到指定的key中，如果add的key不存在，则不会更新数据(过期的key会更新)，之前的值仍保持相同，并且获得响应NOT_STORED
        if(!memcachedClient.add("hello",0,"dennis")){
            System.out.println("Add error,key is existed");
        }
        //replace用于替换已存在的key的value，如果key不存在，则替换失败，并且获得响应NOT_STORED
        if(!memcachedClient.replace("hello",0,"dennis")){
            System.out.println("replace error");
        }
        //用于向已存在的key的value后面添加数据
        memcachedClient.append("hello","good");
        //用于向已存在的key的value之前添加数据
        memcachedClient.prepend("hello","hello");
        String name = memcachedClient.get("hello",new StringTranscoder());
        System.out.println(name);
        //删除数据不用返回应答，适用于批量处理
        memcachedClient.deleteWithNoReply("hello");
    }

    //原子递增或递减
    @Test
    public void testIncrDecr() throws Exception{
        //防止数据干扰，先清空两个key的值
        memcachedClient.delete("Incr");
        memcachedClient.delete("Decr");
        //第一个参数指定了递增key的名称，第二个指定递增的幅度大小，第三个指定了当key不存在的时候的初始值
        System.out.println(memcachedClient.incr("Incr",6,12));
        //重载方法的第三个参数默认为0
        System.out.println(memcachedClient.incr("Incr",3));
        System.out.println(memcachedClient.incr("Incr",2));
        System.out.println(memcachedClient.decr("Decr",1,6));
        System.out.println(memcachedClient.decr("Decr",2));
    }

    //适用于在高并发场景下做并发控制
    @Test
    public void testCounter() throws Exception{
        //第一个参数为计数器的key,第二个为key不存在时的默认值
        Counter counter = memcachedClient.getCounter("counter",10);
        System.out.println("counter="+counter.get());
        //计数器加1
        System.out.println(counter.incrementAndGet());
        //计数器减1
        System.out.println(counter.decrementAndGet());
        //根据传入的值的正负来决定增加或多少这个值
        System.out.println(counter.addAndGet(-10));
    }

    //memcached通过cas协议实现原子更新
    @Test
    public void testCas() throws Exception{
        memcachedClient.set("cas",0,100);
        GetsResponse<Integer> result = memcachedClient.gets("cas");
        System.out.println("result value:"+result);
        long cas = result.getCas();
        //尝试将值更新为200
        if(!memcachedClient.cas("cas",0,200,cas)){
            System.out.println("cas error");
        }
        System.out.println("cas value:"+memcachedClient.get("cas"));
        memcachedClient.cas("cas", 0, new CASOperation<Integer>() {
            //设置最大尝试次数
            @Override
            public int getMaxTries() {
                return 1;
            }

            //根据GetsResponse来决定更新数据的方法,如果更新成功，返回的值将被存储成功
            @Override
            public Integer getNewValue(long currentCAS, Integer currentValue) {
                return 300;
            }
        });
        System.out.println("cas value:"+memcachedClient.get("cas"));
    }

    //更新缓存数据的超时时间
    @Test
    public void testTouch() throws Exception{
        memcachedClient.set("Touch",2,"Touch Value");
        Thread.sleep(1000);
        memcachedClient.touch("Touch",6);
        Thread.sleep(2000);
        System.out.println((String) memcachedClient.get("Touch",3000));
    }

    //查看统计信息，返回存储了所有已经连接并且有效的memcached节点返回的具体信息
    @Test
    public void testStat() throws Exception{
        Map<InetSocketAddress, Map<String,String>> result = memcachedClient.getStats();
        System.out.println("stats="+result.toString());
        /*Map<InetSocketAddress,Map<String,String>> items=memcachedClient.getStatsByItem("items");
        System.out.println("items=" + items.toString());*/
    }

}

