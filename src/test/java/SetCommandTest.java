import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.weixiao.RedisObject;
import org.weixiao.struct.RedisDatabase;

import java.util.HashSet;
import java.util.Set;

import static org.weixiao.util.RedisObjectUtil.parseStringRedisObject;
import static org.weixiao.util.RedisObjectUtil.wrapStringRedisObject;

/**
 * @Date 2023/9/18 16:29
 * @Created by weixiao
 */
public class SetCommandTest {
    RedisDatabase database = new RedisDatabase();

    String setName = "test";
    String value = "100";
    String value2 = "200";

    @Test
    public void saddAndSmembersCommandTest() {
        database.sadd(wrapStringRedisObject(setName), wrapStringRedisObject(value));
        database.sadd(wrapStringRedisObject(setName), wrapStringRedisObject(value2));

        Set<String> addedData = new HashSet<>();
        addedData.add(value);
        addedData.add(value2);

        RedisObject redisObject = database.smembers(wrapStringRedisObject(setName));
        HashSet<RedisObject> setData = (HashSet<RedisObject>) redisObject.getData();
        for (RedisObject data : setData) {
            Assertions.assertTrue(addedData.contains(parseStringRedisObject(data)));
        }

    }

    @Test
    public void sremAndSmembersCommandTest() {
        database.sadd(wrapStringRedisObject(setName), wrapStringRedisObject(value));
        database.sadd(wrapStringRedisObject(setName), wrapStringRedisObject(value2));

        database.srem(wrapStringRedisObject(setName), wrapStringRedisObject(value2));

        Set<String> addedData = new HashSet<>();
        addedData.add(value);

        RedisObject redisObject = database.smembers(wrapStringRedisObject(setName));
        HashSet<RedisObject> setData = (HashSet<RedisObject>) redisObject.getData();
        Assertions.assertEquals(1, setData.size());
        for (RedisObject data : setData) {
            Assertions.assertTrue(addedData.contains(parseStringRedisObject(data)));
        }
    }
}
