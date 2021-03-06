package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dto.AccessTokenDTO;
import com.jxnd.yuhaojun.blog.dto.GithubUserDTO;
import com.jxnd.yuhaojun.blog.provider.GithubProvider;
import com.jxnd.yuhaojun.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@Slf4j
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;
    @Value("${Github.Client_id}")
    private String Client_id;
    @Value("${Github.Client_secret}")
    private String Client_secret;
    @Value("${Github.Redirect_uri}")
    private String Redirect_uri;

    //向github发送post请求以获取access_token
    @GetMapping("callback")
    public String callback(String code, String state, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        String access_token = githubProvider.getAccesstoken(accessTokenDTO);
        GithubUserDTO githubUserDTO = githubProvider.getUser(access_token);
        if (githubUserDTO != null) {
            //如果能够成功得到用户信息,则把该信息存储在session中,并跳转回主页面
            userService.service(githubUserDTO, response);
            return "redirect:/index";
        } else {
            log.error("callback get github error {}", githubUserDTO);
            //登陆失败
            return "redirect:/index";
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index";
    }
}
