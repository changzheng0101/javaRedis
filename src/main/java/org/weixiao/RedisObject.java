package org.weixiao;

import org.weixiao.common.RedisObjectEncoding;
import org.weixiao.common.RedisObjectType;

import java.time.Instant;
import java.util.Objects;

/**
 * @Date 2023/9/15 9:22
 * @Created by weixiao
 */
public class RedisObject {
    private RedisObjectType type;
    private RedisObjectEncoding encoding;
    private Instant lastVisitTime;
    private Object data;

    public RedisObject(RedisObjectType type, RedisObjectEncoding encoding, Instant lastVisitTime, Object data) {
        this.type = type;
        this.encoding = encoding;
        this.lastVisitTime = lastVisitTime;
        this.data = data;
    }

    public RedisObjectType getType() {
        return type;
    }

    public void setType(RedisObjectType type) {
        this.type = type;
    }

    public RedisObjectEncoding getEncoding() {
        return encoding;
    }

    public void setEncoding(RedisObjectEncoding encoding) {
        this.encoding = encoding;
    }

    public Instant getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Instant lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedisObject that = (RedisObject) o;
        return type == that.type && encoding == that.encoding && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, encoding, data);
    }
}
