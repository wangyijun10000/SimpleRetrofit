package com.wyj.simpleretrofit;

import org.json.JSONException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * 模拟retrofit的动态代理处理
 */
public class Retrofit {
    Call call;
    String url = "";

    public <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class[]{service}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Annotation[] annotations = method.getAnnotations();
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof GET) {
                                url = "GET " + ((GET) annotation).value();
                            } else if (annotation instanceof POST) {
                                url = "POST " + ((POST) annotation).value();
                            }
                        }
                        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                        for (int i = 0; i < parameterAnnotations.length; i++) {
                            Annotation[] parameterAnnotations1 = parameterAnnotations[i];
                            for (int j = 0; j < parameterAnnotations1.length; j++) {
                                Annotation annotation = parameterAnnotations1[j];
                                if (annotation instanceof Query) {
                                    url += "&" + ((Query) annotation).value();
                                }
                            }
                            url += "=" + args[i];
                        }
                        call = new Call() {
                            @Override
                            public void enqueue(Callback callback) {
                                if (new Random().nextBoolean()) {
                                    callback.onResponse(call, new Response(url));
                                } else {
                                    callback.onFailure(call, new JSONException("json解析异常"));
                                }
                            }
                        };
                        return call;
                    }
                });
    }
}