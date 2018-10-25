package com.wyj.simpleretrofit;

/**
 * 模拟请求返回数据
 *
 * @param <T>
 */
public final class Response<T> {
    private final String body;

    public Response(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}