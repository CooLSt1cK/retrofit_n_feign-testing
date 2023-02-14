package org.example_feign.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        Exception exception = defaultErrorDecoder.decode(s, response);

        if (exception instanceof RetryableException) {
            return exception;
        }

        if (response.status() >= 500) {
            return new RetryableException(response.status(), "error", response.request().httpMethod(), null, response.request());
        }

        return exception;
    }
}
