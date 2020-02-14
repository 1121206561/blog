package com.jxnd.yuhaojun.blog.provider;

import com.alibaba.fastjson.JSON;
import com.jxnd.yuhaojun.blog.Util.SslUtils;
import com.jxnd.yuhaojun.blog.dto.AccessTokenDTO;
import com.jxnd.yuhaojun.blog.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class GithubProvider {
    public String getAccesstoken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String access_token = response.body().string();
            String token = access_token.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUserDTO getUser(String access_token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + access_token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String user = response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(user, GithubUserDTO.class);
            return githubUserDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
