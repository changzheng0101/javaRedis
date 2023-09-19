package org.weixiao.common;

import org.weixiao.exceptions.UnAuthException;

public interface Constant {
    String[] SIDE_EFFECT_COMMAND = {
            "set", "del", "flushall",
            "hset", "hdel",
            "lpush", "rpush", "lpop", "rpop",
            "sadd", "srem"
    };
}
