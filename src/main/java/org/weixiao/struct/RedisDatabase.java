package org.weixiao.struct;

import org.weixiao.common.RedisObject;
import org.weixiao.struct.dict.DictHt;

import java.time.Instant;
import java.util.*;

import static org.weixiao.util.RedisObjectUtil.*;

/**
 * @Date 2023/9/15 9:38
 * @Created by weixiao
 */
public class RedisDatabase {
    // 数据库键空间，保存着数据库中的所有键值对
    private DictHt<RedisObject, RedisObject> data;
    // 键的过期时间，字典的键为键，字典的值为过期时间
    private DictHt<RedisObject, Instant> expireKeys;

    public RedisDatabase() {
        this.data = new DictHt<>();
        this.expireKeys = new DictHt<>();
    }

    public DictHt<RedisObject, RedisObject> getData() {
        return data;
    }

    public void setData(DictHt<RedisObject, RedisObject> data) {
        this.data = data;
    }

    public DictHt<RedisObject, Instant> getExpireKeys() {
        return expireKeys;
    }

    public void setExpireKeys(DictHt<RedisObject, Instant> expireKeys) {
        this.expireKeys = expireKeys;
    }

    //////////////////////// hash command ///////////////////////////////
    public void hset(RedisObject hashName, RedisObject value) {
        RedisObject redisObject = data.get(hashName);
        if (redisObject != null) {
            DictHt<RedisObject, RedisObject> originHashData = (DictHt<RedisObject, RedisObject>) redisObject.getData();
            DictHt<RedisObject, RedisObject> newHashData = (DictHt<RedisObject, RedisObject>) value.getData();
            Set<RedisObject> newHashDataKeys = newHashData.keys();
            for (RedisObject key : newHashDataKeys) {
                originHashData.put(key, newHashData.get(key));
            }
            value = wrapHashRedisObject(originHashData);
        }
        data.put(hashName, value);
    }

    public RedisObject hget(RedisObject hashName, RedisObject key) {
        RedisObject redisObject = data.get(hashName);
        DictHt<RedisObject, RedisObject> data = (DictHt<RedisObject, RedisObject>) redisObject.getData();
        return data.get(key);
    }

    public RedisObject hgetall(RedisObject hashName) {
        return data.get(hashName);
    }

    public boolean hdel(RedisObject hashName, RedisObject key) {
        RedisObject redisObject = data.get(hashName);
        DictHt<RedisObject, RedisObject> data = (DictHt<RedisObject, RedisObject>) redisObject.getData();
        data.del(key);
        return true;
    }


    public boolean hexists(RedisObject hashName, RedisObject key) {
        RedisObject redisObject = data.get(hashName);
        DictHt<RedisObject, RedisObject> data = (DictHt<RedisObject, RedisObject>) redisObject.getData();
        return data.exists(key);
    }

    //////////////////////// list command ///////////////////////////////
    public void lpush(RedisObject listName, RedisObject value) {
        RedisObject redisObject = data.get(listName);
        LinkedList<RedisObject> redisObjects = new LinkedList<>();
        if (redisObject != null) {
            redisObjects = (LinkedList<RedisObject>) redisObject.getData();
        }
        redisObjects.addFirst(value);
        data.put(listName, wrapLinkedListRedisObject(redisObjects));
    }

    public void rpush(RedisObject listName, RedisObject value) {
        RedisObject redisObject = data.get(listName);
        LinkedList<RedisObject> redisObjects = new LinkedList<>();
        if (redisObject != null) {
            redisObjects = (LinkedList<RedisObject>) redisObject.getData();
        }
        redisObjects.addLast(value);
        data.put(listName, wrapLinkedListRedisObject(redisObjects));
    }

    public RedisObject lpop(RedisObject listName) {
        RedisObject redisObject = data.get(listName);
        if (redisObject != null) {
            LinkedList<RedisObject> data = (LinkedList<RedisObject>) redisObject.getData();
            if (data.size() > 0) {
                return data.removeFirst();
            }
        }
        return null;
    }

    public RedisObject rpop(RedisObject listName) {
        RedisObject redisObject = data.get(listName);
        if (redisObject != null) {
            LinkedList<RedisObject> data = (LinkedList<RedisObject>) redisObject.getData();
            if (data.size() > 0) {
                return data.removeLast();
            }
        }
        return null;
    }

    //////////////////////// set command ///////////////////////////////
    public void sadd(RedisObject setName, RedisObject value) {
        RedisObject redisObject = data.get(setName);
        Set<RedisObject> hashSet = new HashSet<>();
        if (redisObject != null) {
            hashSet = (HashSet<RedisObject>) redisObject.getData();
        }
        hashSet.add(value);
        data.put(setName, wrapHashSetRedisObject(hashSet));
    }

    public RedisObject smembers(RedisObject setName) {
        return data.get(setName);
    }

    public void srem(RedisObject setName, RedisObject value) {
        RedisObject redisObject = data.get(setName);
        Set<RedisObject> hashSet = new HashSet<>();
        if (redisObject != null) {
            hashSet = (HashSet<RedisObject>) redisObject.getData();
        }
        hashSet.remove(value);
        data.put(setName, wrapHashSetRedisObject(hashSet));
    }
}
