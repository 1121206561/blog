package com.jxnd.yuhaojun.blog.controller;

import com.jxnd.yuhaojun.blog.dto.FileDTO;
import com.jxnd.yuhaojun.blog.exception.CustomizeErrorCode;
import com.jxnd.yuhaojun.blog.exception.CustomizeException;
import com.jxnd.yuhaojun.blog.provider.QcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private QcloudProvider qcloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO img(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        //将图片上传到腾讯云
        try {
            String url = qcloudProvider.FileToCos(file.getInputStream());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setMessage("成功");
            fileDTO.setUrl(url);
            return fileDTO;
        } catch (IOException e) {
           throw new CustomizeException(CustomizeErrorCode.NO_UPLOAD_IMG);
        }
    }
}
