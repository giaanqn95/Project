package com.intelin.vn.demomvvm.exception;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 25/03/2019
 * Time: 1:53 PM
 */
public class CustomException {

    public static class EncodingException extends BaseException {

        public EncodingException(String mess) {
            super(mess);
        }

        public EncodingException(String mess, Throwable cause) {
            super(mess, cause);
        }
    }

    public static class DecodingException extends BaseException {

        public DecodingException(String mess) {
            super(mess);
        }

        public DecodingException(String mess, Throwable cause) {
            super(mess, cause);
        }
    }

    public static class CheckValueException extends BaseException {

        public CheckValueException(String mess) {
            super(mess);
        }

        public CheckValueException(String mess, Throwable cause) {
            super(mess, cause);
        }
    }
}
