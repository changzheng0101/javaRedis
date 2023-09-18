import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.weixiao.RedisObject;
import org.weixiao.struct.RedisDatabase;

import static org.weixiao.util.RedisObjectUtil.*;

/**
 * @Date 2023/9/18 15:21
 * @Created by weixiao
 */
public class ListCommandTest {

    RedisDatabase database = new RedisDatabase();

    String ListName = "test";
    String value = "100";
    String value2 = "200";

    @Test
    public void lpushAndlpopCommandTest() {
        database.lpush(wrapStringRedisObject(ListName), wrapStringRedisObject(value));
        RedisObject redisObject = database.lpop(wrapStringRedisObject(ListName));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
    }

    @Test
    public void rpushAndRpopCommandTest() {
        database.rpush(wrapStringRedisObject(ListName), wrapStringRedisObject(value));
        RedisObject redisObject = database.rpop(wrapStringRedisObject(ListName));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
    }

    @Test
    public void lpushAndRpopCommandTest() {
        database.lpush(wrapStringRedisObject(ListName), wrapStringRedisObject(value));
        RedisObject redisObject = database.rpop(wrapStringRedisObject(ListName));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
    }

    @Test
    public void rpushAndlpopCommandTest() {
        database.lpush(wrapStringRedisObject(ListName), wrapStringRedisObject(value));
        RedisObject redisObject = database.lpop(wrapStringRedisObject(ListName));
        Assertions.assertEquals(value, parseStringRedisObject(redisObject));
    }

    @Test
    public void multiLpushAndlpopCommandTest() {
        database.lpush(wrapStringRedisObject(ListName), wrapStringRedisObject(value));
        database.lpush(wrapStringRedisObject(ListName), wrapStringRedisObject(value2));
        RedisObject redisObject = database.lpop(wrapStringRedisObject(ListName));
        Assertions.assertEquals(value2, parseStringRedisObject(redisObject));
    }

}
