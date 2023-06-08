package com.ZHL.TaTa.controller.ex;

import com.ZHL.TaTa.service.ex.ServiceException;


public class CodeNotMatchException extends ServiceException {
    public CodeNotMatchException() {
        super();
    }

    public CodeNotMatchException(String message) {
        super(message);
    }

    public CodeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeNotMatchException(Throwable cause) {
        super(cause);
    }

    protected CodeNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
