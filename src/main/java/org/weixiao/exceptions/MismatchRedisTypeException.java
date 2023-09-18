package org.weixiao.exceptions;

/**
 * @Date 2023/9/18 10:01
 * @Created by weixiao
 */
public class MismatchRedisTypeException extends RuntimeException {
    public MismatchRedisTypeException() {
        super("类型不匹配");
    }
}
