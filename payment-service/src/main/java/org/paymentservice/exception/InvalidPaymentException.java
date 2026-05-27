package org.paymentservice.exception;

public class InvalidPaymentException
        extends RuntimeException {

    public InvalidPaymentException(String message) {
        super(message);
    }
}