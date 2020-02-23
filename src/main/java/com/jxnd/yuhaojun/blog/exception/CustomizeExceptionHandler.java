package com.jxnd.yuhaojun.blog.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
//拦截部分运行时的异常信息
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof CustomizeException) {
            model.addAttribute("message", "你请求的问题不存在,请稍后再试试！！！");
        } else {
            model.addAttribute("message", "服务冒烟了,请稍后再来访问！！！！");
        }
        return new ModelAndView("error");
    }
}