package com.jxnd.yuhaojun.blog.config;

import com.jxnd.yuhaojun.blog.dao.UserDAO;
import com.jxnd.yuhaojun.blog.model.Ad;
import com.jxnd.yuhaojun.blog.model.User;
import com.jxnd.yuhaojun.blog.service.AdService;
import com.jxnd.yuhaojun.blog.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class SessionInterceptors implements HandlerInterceptor {
    @Autowired
    private UserDAO dao;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AdService adService;

    //拦截器对规定的网页访问时进行规定的处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<Ad> adList = (List<Ad>) request.getSession().getAttribute("ads");
        if (adList == null) {
            List<Ad> ads = adService.selectAll();
            request.getSession().setAttribute("ads", ads);
        }
        //每次进入主页面时就会对数据库进行查询是否你已经成功登录过
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = dao.select(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        Long Count = notificationService.selectByStatus(user.getLogin());
                        request.getSession().setAttribute("count", Count);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
