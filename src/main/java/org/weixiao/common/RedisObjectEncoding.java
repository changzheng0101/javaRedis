package org.weixiao.common;

/**
 * @Date 2023/9/15 9:32
 * @Created by weixiao
 */
public enum RedisObjectEncoding {
    REDIS_ENCODING_INT,
    REDIS_ENCODING_STRING,
    // hashtable -- dict实现
    REDIS_ENCODING_HT,
    REDIS_ENCODING_LINKED_LIST,
    // ziplist and intset
    REDIS_ENCODING_ARRAY_LIST,
    REDIS_ENCODING_SKIP_LIST,
}
