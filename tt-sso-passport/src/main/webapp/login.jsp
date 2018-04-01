<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单点登录系统</title>
</head>
<body>
<div id="loginbar"></div>
<div id="loginf" >
    <form action="http://localhost:9087/ttshop/login.d">
        <input name="un" placeholder="请输入账号">
        <br/>
        <input name="pw" placeholder="请输入密码" type="password"><br/>
        <input type="submit" value="登录">
    </form>
</div>
<script type="application/javascript" src="https://cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
<script type="application/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
</body>
</html>
