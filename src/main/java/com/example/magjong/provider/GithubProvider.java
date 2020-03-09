package com.example.magjong.provider;

import com.alibaba.fastjson.JSONObject;
import com.example.magjong.dto.AccessTokenDTO;
import com.example.magjong.dto.User;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public String getAccessToken(AccessTokenDTO access) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, JSONObject.toJSONString(access));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
                Response response = client.newCall(request).execute();
        String token = response.body().string();
        String[] split = token.split("&");
        String[] split1 = split[0].split("=");
        return split1[1];
    }
    public User tokenGetGithubUserInfo(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
//                    https://api.github.com/user?access_token=c2557d4b3500134d8acc2be97435eccd720d65b1
                .build();
        try {
            Response response = client.newCall(request).execute();
            String user_info = response.body().string();
            User user = JSONObject.parseObject(user_info, User.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
