package org.weixiao.struct;

import org.weixiao.struct.dict.DictHt;

import java.time.Instant;

/**
 * @Date 2023/9/15 9:38
 * @Created by weixiao
 */
public class RedisDatabase {
    // 数据库键空间，保存着数据库中的所有键值对
    private DictHt<String, Object> data;
    // 键的过期时间，字典的键为键，字典的值为过期时间
    private DictHt<String, Instant> expireKeys;
}
