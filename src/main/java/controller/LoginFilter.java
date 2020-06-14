package controller;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wk
 * @title: LoginFilter
 * @projectName Electroniclibrary
 * @description: TODO
 * @date 2020/6/1421:26
 */
public class LoginFilter implements Filter {
    // 初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");

    }

    // 拦截处理
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截处理");
    }

    // 销毁
    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
