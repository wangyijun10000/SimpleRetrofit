package com.wyj.simpleretrofit;

public interface Call<T> extends Cloneable {
  void enqueue(Callback<T> callback);
}