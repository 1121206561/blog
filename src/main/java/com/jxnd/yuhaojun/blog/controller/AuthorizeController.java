package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dto.AccessTokenDTO;
import com.jxnd.yuhaojun.blog.dto.GithubUserDTO;
import com.jxnd.yuhaojun.blog.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    //向github发送post请求以获取access_token
    @GetMapping("callback")
    public String callback(String code, String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("1885fcec32c1656b5e00");
        accessTokenDTO.setClient_secret("e88e7999cebe9defbf3018d9c390d128308d3aa3");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String access_token = githubProvider.getAccesstoken(accessTokenDTO);
        GithubUserDTO githubUserDTO = githubProvider.getUser(access_token);
        return "index";
    }
}
