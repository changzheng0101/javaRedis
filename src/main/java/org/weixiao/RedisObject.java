package org.weixiao;

import org.weixiao.common.RedisObjectEncoding;
import org.weixiao.common.RedisObjectType;

import java.time.Instant;

/**
 * @Date 2023/9/15 9:22
 * @Created by weixiao
 */
public class RedisObject<E> {
    private RedisObjectType type;
    private RedisObjectEncoding encoding;
    private Instant lastVisitTime;
    private E data;
}
