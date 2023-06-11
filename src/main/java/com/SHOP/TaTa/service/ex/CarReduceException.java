package com.SHOP.TaTa.service.ex;


public class CarReduceException extends ServiceException{
    public CarReduceException() {
        super();
    }

    public CarReduceException(String message) {
        super(message);
    }

    public CarReduceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarReduceException(Throwable cause) {
        super(cause);
    }

    protected CarReduceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
