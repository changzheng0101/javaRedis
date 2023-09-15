package org.weixiao.struct;

import org.weixiao.struct.dict.DictHt;

import java.time.Instant;

/**
 * @Date 2023/9/15 9:38
 * @Created by weixiao
 */
public class RedisDatabase {
    // 数据库键空间，保存着数据库中的所有键值对
    private DictHt<Object> data;
    // 键的过期时间，字典的键为键，字典的值为过期时间
    private DictHt<Instant> expireKeys;

    public RedisDatabase() {
        this.data = new DictHt<>();
        this.expireKeys = new DictHt<>();
    }

    public DictHt<Object> getData() {
        return data;
    }

    public void setData(DictHt<Object> data) {
        this.data = data;
    }

    public DictHt<Instant> getExpireKeys() {
        return expireKeys;
    }

    public void setExpireKeys(DictHt<Instant> expireKeys) {
        this.expireKeys = expireKeys;
    }
}
