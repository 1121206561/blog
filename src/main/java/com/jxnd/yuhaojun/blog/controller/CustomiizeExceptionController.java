package com.jxnd.yuhaojun.blog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//如果是404或者500的错误就会跳转到该页面并且呈现报错信息
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomiizeExceptionController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(Model model, HttpServletRequest servletRequest) {
        HttpStatus status = getStatus(servletRequest);
        if (status.is4xxClientError()) {
            model.addAttribute("message", "请求出错了,请稍后再试试");
        }
        if (status.is5xxServerError()) {
            model.addAttribute("message", "服务器出错了,请稍后再试试");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest servletRequest) {
        Integer statusCode = (Integer) servletRequest
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
