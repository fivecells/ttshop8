package com.qf.ttshop.sso.servlet;

import com.qf.ttshop.sso.util.RedisUtils;
import com.qf.ttshop.utils.CookieUtils;
import com.qf.ttshop.utils.StrKit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: DHC
 * Date: 2018/2/5
 * Time: 11:55
 * Version:V1.0
 */
@WebServlet("/login.d")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ck = CookieUtils.getCookieValue(req, "p2ptoken");
        if (StrKit.notBlank(ck)) {
            RedisUtils.setTime("p2ptoken:" + ck, 300);
        } else {
            String name = req.getParameter("un");
            String token = StrKit.uuid();
            RedisUtils.set("p2ptoken:"+ token,name,300);
            CookieUtils.setCookie(req,resp,"p2ptoken",token,300);
        }
        resp.sendRedirect("index.jsp");
    }
}
