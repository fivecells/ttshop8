package com.qf.ttshop.service.impl;

import com.qf.ttshop.dto.MessageResult;
import com.qf.ttshop.jedis.JedisClient;
import com.qf.ttshop.pojo.po.TbUser;
import com.qf.ttshop.service.TokenService;
import com.qf.ttshop.utils.JsonUtils;
import com.qf.ttshop.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: DHC
 * Date: 2018/2/2
 * Time: 15:04
 * Version:V1.0
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult getUserByToken(String token) {
        MessageResult mr = new MessageResult();
        String userJson = jedisClient.get("TT_TOKEN:" + token);
        if (StrKit.isBlank(userJson)) {
            //在redis集群中没有找到当前用户
            mr.setSuccess(false);
            mr.setMessage("用户已经过期");
            return mr;
        }
        //登录成功并且没有过期
        jedisClient.expire("TT_TOKEN:" + token, 1800);
        TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
        mr.setSuccess(true);
        mr.setMessage("登录成功");
        mr.setData(user);
        return mr;
    }
}
