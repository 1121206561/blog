package com.jxnd.yuhaojun.blog.exception;

import com.alibaba.fastjson.JSON;
import com.jxnd.yuhaojun.blog.dto.ResultDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
//拦截部分运行时的异常信息
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
        //对不同的访问格式的异常分别进行处理,如果是JSON格式就不跳转只报错,如果是application/text格式的跳转到报错页面
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            if (e instanceof CustomizeException) {
                resultDTO = new ResultDTO();
                resultDTO.setCode(((CustomizeException) e).getCode());
                resultDTO.setMessage(e.getMessage());
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");

        }
    }
}