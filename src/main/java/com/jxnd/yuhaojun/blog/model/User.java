package com.jxnd.yuhaojun.blog.model;

import lombok.Data;
//mysql信息
@Data
public class User {
    private Integer id;
    private String account_id;
    private String login;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
}
