package com.SHOP.TaTa.service.ex;


public class PhoneNotFindException extends ServiceException{
    public PhoneNotFindException() {
        super();
    }

    public PhoneNotFindException(String message) {
        super(message);
    }

    public PhoneNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNotFindException(Throwable cause) {
        super(cause);
    }

    protected PhoneNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
