package controller;

import Dao.ISysUserDao;
import Dao.impl.SysUsersIDao;
import Utils.Md5andBase64Utils;
import bean.SysUsers;

import javax.jws.WebResult;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName LoginServlet
 * @Description servlet实现登录方法
 * @Author wk
 * @Date 2020/6/12 0012 15:58
 * @Version 1.0
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
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
            if(users != null){
                String userpassword = Md5andBase64Utils.decryptBASE64(users.getPassword());
                password = Md5andBase64Utils.encode(password);
                if(userpassword.equals(password)){
                    System.out.println("登录成功");
                    response.sendRedirect("index.jsp");
                    return;
                }else{
                    System.out.println("密码错误");
                    return;
                }
            }else{
                System.out.println("该用户不存在！");
                response.sendRedirect("login.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
