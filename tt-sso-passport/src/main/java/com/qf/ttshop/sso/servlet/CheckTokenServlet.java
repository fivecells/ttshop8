package com.qf.ttshop.sso.servlet;

import com.qf.ttshop.sso.util.RedisUtils;
import com.qf.ttshop.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: DHC
 * Date: 2018/2/5
 * Time: 13:06
 * Version:V1.0
 */
@WebServlet("/checktoken.d")
public class CheckTokenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ck = CookieUtils.getCookieValue(req, "p2ptoken");
        System.out.println(ck + "----->" + RedisUtils.get("p2ptoken:" + ck));
        String callback = req.getParameter("callback");
        String json = "{'id':'" + ck + "','name':'" + RedisUtils.get("p2ptoken:" + ck) + "'}";
        System.err.println("返回：" + json);
        resp.getWriter().print(callback + "(" + json + ")");
    }
}
