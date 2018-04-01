<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<h2>需要登录的首页</h2>
<div id="loginbar"></div>
<script type="application/javascript" src="https://cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
<script type="application/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="application/javascript">

    var ssologin = {
        checkLogin: function () {
            var ton = $.cookie("p2ptoken");
            console.log("令牌：" + ton);
            if (!ton) {
                location.href = "login.jsp";
                return;
            }
            $.ajax({
                url: "http://localhost:9087/ttshop/checktoken.d?token=" + ton,
                dataType: "jsonp",
                type: "GET",
                success: function getData(result) {
                    var html = result.name + "，欢迎来到SSO系统！<a href=\"http://localhost:9087/ttshop/loginout.d\">[退出]</a>";
                    $("#loginbar").html(html);
                }
            });
        }
    };

    $(function () {
        ssologin.checkLogin();
    });
</script>
</body>
</html>
