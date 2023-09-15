package org.weixiao.exceptions;

/**
 * @Date 2023/9/15 9:48
 * @Created by weixiao
 */
public class UnAuthException extends RuntimeException {
    public UnAuthException() {
        super("用户未认证");
    }

    public UnAuthException(String message) {
        super(message);
    }
}
