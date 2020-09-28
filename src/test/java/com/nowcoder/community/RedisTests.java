package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Redis测试类
 * @author 尚郑
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    // 字符串
    @Test
    public void testStrings(){
        String redisKey = "test:count";
        redisTemplate.opsForValue().set(redisKey,1);

        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));
    }

    // 哈希
    @Test
    public void testHashes(){
        String redisKey = "test:user";

        redisTemplate.opsForHash().put(redisKey,"id",1);
        redisTemplate.opsForHash().put(redisKey,"username","张三");

        System.out.println(redisTemplate.opsForHash().get(redisKey,"id"));
        System.out.println(redisTemplate.opsForHash().get(redisKey,"username"));
    }

    // 链表
    @Test
    public void testLists(){
        String redisKey = "test:ids";

        redisTemplate.opsForList().leftPush(redisKey,101);
        redisTemplate.opsForList().leftPush(redisKey,102);
        redisTemplate.opsForList().leftPush(redisKey,103);

        System.out.println(redisTemplate.opsForList().size(redisKey)); // 3
        System.out.println(redisTemplate.opsForList().index(redisKey,0)); // 103
        System.out.println(redisTemplate.opsForList().range(redisKey,0,2)); // [103, 102, 101]

        System.out.println(redisTemplate.opsForList().leftPop(redisKey)); // 103
        System.out.println(redisTemplate.opsForList().leftPop(redisKey)); // 102
        System.out.println(redisTemplate.opsForList().leftPop(redisKey)); // 101

    }

    // 无序集合
    @Test
    public void testSets(){
        String redisKey = "test:teachers";

        redisTemplate.opsForSet().add(redisKey,"小苍","小野","小龙","小关","达斯");

        System.out.println(redisTemplate.opsForSet().size(redisKey));
        System.out.println(redisTemplate.opsForSet().pop(redisKey));
        System.out.println(redisTemplate.opsForSet().members(redisKey)); // [小苍, 小野, 小关, 小龙]
    }

    // 有序
    @Test
    public void testSortedSets(){
        String redisKey = "test:students";

        redisTemplate.opsForZSet().add(redisKey,"小张",100);
        redisTemplate.opsForZSet().add(redisKey,"小李",90);
        redisTemplate.opsForZSet().add(redisKey,"小郑",100);
        redisTemplate.opsForZSet().add(redisKey,"小刘",80);

        System.out.println(redisTemplate.opsForZSet().zCard(redisKey)); // 4
        System.out.println(redisTemplate.opsForZSet().score(redisKey,"小张")); // 100.0
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey,"小张")); // 1 索引 从小到大排列
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey,0,2)); // [小郑, 小张, 小李] 从小到大排列
    }

    @Test
    public void testKeys(){
        redisTemplate.delete("test:user");

        System.out.println(redisTemplate.hasKey("test:user"));
        // 设置过期时间
        redisTemplate.expire("test:students",10, TimeUnit.SECONDS);
    }

    // 多次访问同一个key
    @Test
    public void testBoundOperations(){
        String redisKey = "test:count";
        BoundValueOperations operations = redisTemplate.boundValueOps(redisKey);
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        System.out.println(operations.get());
    }

    // 编程式事务
    @Test
    public void testTransactional(){
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey = "test:tx";
                // 启用事务
                operations.multi();

                operations.opsForSet().add(redisKey,"哈");
                operations.opsForSet().add(redisKey,"哈哈");
                operations.opsForSet().add(redisKey,"哈哈哈");

                System.out.println(operations.opsForSet().members(redisKey));
                return operations.exec();
            }
        });
        System.out.println(obj);
    }
}
