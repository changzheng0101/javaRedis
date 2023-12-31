import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.weixiao.common.RedisObject;
import org.weixiao.struct.RedisDatabase;
import org.weixiao.struct.dict.DictHt;

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
    public void hsetAndHgetCommandTest() {
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key, value));
        // hget 
        RedisObject redisObject = database.hget(wrapStringRedisObject(hashName), wrapStringRedisObject(key));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
    }

    @Test
    public void multiHsetAndHgetCommandTest() {
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

    @Test
    public void multiHsetAndHgetallCommandTest() {
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key, value));
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key2, value2));
        // hget
        RedisObject redisObject = database.hgetall(wrapStringRedisObject(hashName));
        DictHt<RedisObject, RedisObject> data = (DictHt<RedisObject, RedisObject>) redisObject.getData();
        Assertions.assertEquals(data.get(wrapStringRedisObject(key)).getData(), value);
        Assertions.assertEquals(data.get(wrapStringRedisObject(key2)).getData(), value2);
    }

    @Test
    public void hexistsCommandTest() {
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key, value));
        Assertions.assertTrue(database.hexists(wrapStringRedisObject(hashName), wrapStringRedisObject(key)));
        Assertions.assertFalse(database.hexists(wrapStringRedisObject(hashName), wrapStringRedisObject(key2)));
    }

    @Test
    public void hdelCommandTest() {
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key, value));
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key2, value2));
        database.hdel(wrapStringRedisObject(hashName), wrapStringRedisObject(key2));
        Assertions.assertTrue(database.hexists(wrapStringRedisObject(hashName), wrapStringRedisObject(key)));
        Assertions.assertFalse(database.hexists(wrapStringRedisObject(hashName), wrapStringRedisObject(key2)));
    }
}
