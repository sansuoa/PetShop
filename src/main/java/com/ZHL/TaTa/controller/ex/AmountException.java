package com.ZHL.TaTa.controller.ex;

import com.ZHL.TaTa.service.ex.ServiceException;


public class AmountException extends ServiceException {
    public AmountException() {
        super();
    }

    public AmountException(String message) {
        super(message);
    }

    public AmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmountException(Throwable cause) {
        super(cause);
    }

    protected AmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
