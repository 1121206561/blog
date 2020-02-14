package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;

//用于封装向Github获取access_token需要传递的参数
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
