package com.wyj.simpleretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int COUNT = 15;
    IMessageAPI iMessageAPI;
    TextView mContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRetrofit();
        mContentTv = findViewById(R.id.content_tv);
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 模拟登录
                 */
                iMessageAPI.login(System.currentTimeMillis())
                        .enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                mContentTv.setText("获取到数据：" + response.getBody());
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(MainActivity.this, "接口请求错误：" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        findViewById(R.id.message_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 模拟获取数据
                 */
                iMessageAPI.getMessage(COUNT, System.currentTimeMillis() + "")
                        .enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                mContentTv.setText("获取到数据：" + response.getBody());
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(MainActivity.this, "接口请求错误：" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    /**
     * 模拟retrofit初始化
     */
    private void initRetrofit() {
        iMessageAPI = new Retrofit().create(IMessageAPI.class);
    }
}
