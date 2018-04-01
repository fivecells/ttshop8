package com.qf.ttshop.service.impl;

import com.qf.ttshop.dao.TbUserMapper;
import com.qf.ttshop.dto.MessageResult;
import com.qf.ttshop.jedis.JedisClient;
import com.qf.ttshop.pojo.po.TbUser;
import com.qf.ttshop.pojo.po.TbUserExample;
import com.qf.ttshop.service.LoginService;
import com.qf.ttshop.utils.JsonUtils;
import com.qf.ttshop.utils.StrKit;
import org.noggit.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/2/2
 * Time: 9:57
 * Version:V1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbUserMapper userDao;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult userLogin(String username, String password) {
        MessageResult mr = null;
        try {
            //使用查询模板查询用户
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);
            List<TbUser> userList = userDao.selectByExample(example);
            //若list为空，则用户名不存在
            if(userList == null || userList.isEmpty()){
                //用户名不存在
                mr = new MessageResult();
                mr.setSuccess(false);
                mr.setMessage("用户名不存在");
                return mr;
            }
            //用户名存在
            TbUser user = userList.get(0);
            //把前台传递过来的密码进行MD5的加密
            String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
            //前台传递过来的密码与后台密码比较，如果不相同就会进入判断
            if(!md5DigestAsHex.equals(user.getPassword())){
                //用户名正确但是密码错误
                mr = new MessageResult();
                mr.setSuccess(false);
                mr.setMessage("用户名或密码错误");
                return mr;
            }
            //用户名和密码都正确
            //第一步，把要自制的令牌存放到redis集群
            String token = StrKit.uuid();
            user.setPassword(null);
            //TT_TOKEN22222222,{"username":"","password":null,xxxxx}
            jedisClient.set("TT_TOKEN:" + token, JsonUtils.objectToJson(user));
            //第二步，设置过期时间
            jedisClient.expire("TT_TOKEN:" + token,1800);
            //第三步，设置返回值
            mr = new MessageResult();
            mr.setSuccess(true);
            mr.setMessage("登录成功");
            mr.setData(token);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return mr;
    }
}
