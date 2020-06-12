package controller;

import Dao.ISysUserDao;
import Dao.impl.SysUsersIDao;
import bean.SysUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * @ClassName LoginServlet
 * @Description servlet实现登录方法
 * @Author wk
 * @Date 2020/6/12 0012 15:58
 * @Version 1.0
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得用户的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username==null||"".equals(username.trim())||password==null||"".equals(password.trim())){
                System.out.println("用户名或密码不能为空！");
                response.sendRedirect("login.jsp");
                return;
        }

        ISysUserDao userDao = new SysUsersIDao();
        try {
            SysUsers users = userDao.querySysUsers(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
