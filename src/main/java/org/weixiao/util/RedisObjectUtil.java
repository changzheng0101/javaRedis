package org.weixiao.util;

import org.weixiao.RedisObject;
import org.weixiao.common.RedisObjectEncoding;
import org.weixiao.common.RedisObjectType;
import org.weixiao.exceptions.MismatchRedisTypeException;
import org.weixiao.struct.dict.DictHt;

import java.time.Instant;

/**
 * @Date 2023/9/18 10:27
 * @Created by weixiao
 */
public class RedisObjectUtil {
    public static String parseStringRedisObject(RedisObject redisObject) {
        if (redisObject == null) {
            return "(nil)";
        }
        if (redisObject.getEncoding() != RedisObjectEncoding.REDIS_ENCODING_STRING
                || redisObject.getType() != RedisObjectType.REDIS_STRING) {
            throw new MismatchRedisTypeException();
        }
        return redisObject.getData().toString();
    }


    public static RedisObject wrapStringRedisObject(String value) {
        return new RedisObject(
                RedisObjectType.REDIS_STRING,
                RedisObjectEncoding.REDIS_ENCODING_STRING,
                Instant.now(),
                value
        );
    }

    public static RedisObject wrapHashRedisObject(String key, String value) {
        DictHt<RedisObject, RedisObject> data = new DictHt<>();
        data.put(wrapStringRedisObject(key), wrapStringRedisObject(value));
        return new RedisObject(
                RedisObjectType.REDIS_HASH,
                RedisObjectEncoding.REDIS_ENCODING_HT,
                Instant.now(),
                data
        );
    }

    public static RedisObject wrapHashRedisObject(DictHt<RedisObject, RedisObject> data) {
        return new RedisObject(
                RedisObjectType.REDIS_HASH,
                RedisObjectEncoding.REDIS_ENCODING_HT,
                Instant.now(),
                data
        );
    }
}
