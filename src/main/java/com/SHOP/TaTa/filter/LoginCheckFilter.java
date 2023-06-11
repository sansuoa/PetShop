package com.SHOP.TaTa.filter;

import com.alibaba.fastjson.JSON;
import com.SHOP.TaTa.common.BaseContext;
import com.SHOP.TaTa.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login",
                "/front/index.html",
                "/user/**",
                "/orders/**",
                "/test/**",
                "/test/page/**",
                "/petSale/",
                "/address/**",
                "/petSale/newUpdateSale",
                "/petLeave/newUpdateSale",
                "/category/listAll",
                "/petSale/{id}/details",
                "/cart/addToCart",
                "/favicon.ico",
                "/cart/",
                "/cart/{id}/num/add",
                "/cart/**",
                "/orders/create",
                "/petSale/listall",
                "/orders/list",
                "/petSale/listByCateGoryId/**",
                "/notice/list",
                "/comments/**",
                "/petSale/findEightDogFoodSale",
                "/petSale/**",
                "/petLeave/**/details",
                "/petLeave/listall",
                "/category/listLeave",
                "/petLeave/listByCateGoryId/**",
                "/petSale/**/someWithSale",
                "/petSale/findEightUseSale",
                "/petSale/findEightToySale",
                "/petSale/findEightCatFoodSale",
        };

        boolean check = check(urls, requestURI);

        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录，id为：{}",request.getSession().getAttribute("employee"));
            Long empid = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empid);
            long id = Thread.currentThread().getId();
            log.info("线程id为:{}",id);
            filterChain.doFilter(request,response);
            return;
        }

        if(request.getSession().getAttribute("user")!=null){
            log.info("用户已登录，id为：{}",request.getSession().getAttribute("user"));
            Long userid = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userid);
            long id = Thread.currentThread().getId();
            log.info("线程id为:{}",id);
            filterChain.doFilter(request,response);
            return;
        }
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
