<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
</head>
<body>
<form action="./login.do" method="post">
    <br />
        <label>用户名：</label>
        <input type="text" name="username" placeholder="请输入用户名" />
    <br />
        <label>密码：</label>
        <input type="text" name="password" placeholder="请输入密码" /><br />
    <input type="submit" value="login" name="" />

</form>
</body>
</html>