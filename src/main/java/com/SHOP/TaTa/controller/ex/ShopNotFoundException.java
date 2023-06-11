package com.SHOP.TaTa.controller.ex;

import com.SHOP.TaTa.service.ex.ServiceException;


public class ShopNotFoundException extends ServiceException {
    public ShopNotFoundException() {
        super();
    }

    public ShopNotFoundException(String message) {
        super(message);
    }

    public ShopNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ShopNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
