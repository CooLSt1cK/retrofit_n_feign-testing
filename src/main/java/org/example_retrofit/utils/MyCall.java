package org.example_retrofit.utils;

import retrofit2.Response;

import java.io.IOException;

public interface MyCall<T> {

    void cancel();

    void enqueue(MyCallback<T> callback);

    MyCall<T> clone();
}
