package com.jxnd.yuhaojun.blog.dto;

import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage(errorCode.getMessage());
        resultDTO.setCode(errorCode.getCode());
        return resultDTO;
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("发布信息成功");
        return resultDTO;
    }
}
