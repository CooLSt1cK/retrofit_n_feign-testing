package org.example_retrofit.utils;

public interface MyCall<T> {

    void cancel();

    void enqueue(MyCallback<T> callback);

    MyCall<T> clone();
}
