package com.slayerd.exceptions;

/**
 * Throw the exception when generate random id failure
 * @author slayerd
 * @since 2023-04-03
 * @version 1.0
 */
public class IdGenerateFailureException extends Throwable {
    public IdGenerateFailureException(String message) {
        super(message);
    }
}
