package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;

@Data
//用于封装向Github获取user需要传递的参数
public class GithubUserDTO {
    private String login;
    private String node_id;
    private Long id;
    private String avatar_url;
}
