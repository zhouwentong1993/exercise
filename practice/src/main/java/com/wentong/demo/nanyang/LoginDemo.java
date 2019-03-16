package com.wentong.demo.nanyang;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

public class LoginDemo {
    @Test
    public void test() {
        String url = "http://www.nanyangquiz.com/login";
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("username", "13027393366")
                .add("password", "值")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
