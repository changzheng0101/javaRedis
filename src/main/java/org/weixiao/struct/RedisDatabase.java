package org.weixiao.struct;

import org.weixiao.RedisObject;
import org.weixiao.struct.dict.DictHt;

import java.time.Instant;

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
}
