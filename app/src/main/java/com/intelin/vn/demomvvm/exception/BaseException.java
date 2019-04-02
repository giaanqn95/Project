package com.intelin.vn.demomvvm.exception;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 25/03/2019
 * Time: 3:02 PM
 */
public class BaseException extends RuntimeException {

    public BaseException(String mess) {
        super(mess);
    }

    public BaseException(String mess, Throwable cause) {
        super(mess, cause);
    }
}
