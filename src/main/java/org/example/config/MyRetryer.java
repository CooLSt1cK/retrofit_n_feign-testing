package org.example.config;

import feign.RetryableException;
import feign.Retryer;

public class MyRetryer implements feign.Retryer {

    @Override
    public void continueOrPropagate(RetryableException e) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    @Override
    public Retryer clone() {
        return new MyRetryer();
    }
}
