package org.hut.common.exception;

/**
 * Created by hutwanghui on 2018/11/24 15:40.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc:
 */

public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckedException() {
    }

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
