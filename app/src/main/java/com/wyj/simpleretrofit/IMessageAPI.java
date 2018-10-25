package com.wyj.simpleretrofit;

/**
 * 模拟请求接口
 */
public interface IMessageAPI {

    @GET("/login")
    Call login(@Query("uuid") long UUID);

    @POST("/getMessage")
    Call getMessage(@Query("count") int count, @Query("token") String token);
}