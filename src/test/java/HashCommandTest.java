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

    @Test
    public void hsetAndHsetCommandTest() {
        String hashName = "test";
        String key = "a";
        String value = "100";
        database.hset(wrapStringRedisObject(hashName),
                wrapHashRedisObject(key, value));
        // hget 
        RedisObject redisObject = database.hget(wrapStringRedisObject(hashName), wrapStringRedisObject(key));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
    }
}
