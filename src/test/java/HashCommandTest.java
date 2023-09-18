import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.weixiao.RedisObject;
import org.weixiao.struct.RedisDatabase;

import static org.weixiao.util.RedisObjectUtil.*;


/**
 * @Date 2023/9/18 10:25
 * @Created by weixiao
 */
public class HashCommandTest {
    RedisDatabase database = new RedisDatabase();

    String hashName = "test";
    String key = "a";
    String value = "100";
    String key2 = "b";
    String value2 = "200";

    @Test
    public void hsetAndHsetCommandTest() {
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key, value));
        // hget 
        RedisObject redisObject = database.hget(wrapStringRedisObject(hashName), wrapStringRedisObject(key));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
    }

    @Test
    public void multiHsetAndHsetCommandTest() {
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key, value));
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key2, value2));
        // hget
        RedisObject redisObject = database.hget(wrapStringRedisObject(hashName), wrapStringRedisObject(key));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
        redisObject = database.hget(wrapStringRedisObject(hashName), wrapStringRedisObject(key2));
        Assertions.assertEquals(value2, parseStringRedisObject(redisObject));
    }
}
