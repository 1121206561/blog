package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO img() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("成功");
        fileDTO.setUrl("/img/lol.png");
        return fileDTO;
    }
}
