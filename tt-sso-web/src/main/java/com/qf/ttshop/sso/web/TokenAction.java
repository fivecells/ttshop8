package com.qf.ttshop.sso.web;

import com.qf.ttshop.dto.MessageResult;
import com.qf.ttshop.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: DHC
 * Date: 2018/2/2
 * Time: 15:00
 * Version:V1.0
 */
@Controller
public class TokenAction {

    @Autowired
    private TokenService tokenService;

    @ResponseBody
    @RequestMapping(value = "/user/{token}",method = RequestMethod.GET)
    public MessageResult getUserByToken(@PathVariable("token") String token){
        MessageResult mr = tokenService.getUserByToken(token);
        return mr;
    }
}
