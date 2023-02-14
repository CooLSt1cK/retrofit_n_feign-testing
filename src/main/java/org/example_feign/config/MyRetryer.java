package org.example_feign.config;

import feign.RetryableException;
import feign.Retryer;

public class MyRetryer implements feign.Retryer {

    private int retryMaxAttempt;

    private long retryInterval;

    private int attempt = 1;


    public MyRetryer(int retryMaxAttempt, Long retryInterval) {
        this.retryMaxAttempt = retryMaxAttempt;
        this.retryInterval = retryInterval;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ == retryMaxAttempt) {
            throw e;
        }
        try {
            Thread.sleep(retryInterval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }

    }

    @Override
    public Retryer clone() {
        return new MyRetryer(6, 2000L);
    }
}

