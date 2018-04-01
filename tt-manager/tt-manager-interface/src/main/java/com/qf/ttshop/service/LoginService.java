package com.qf.ttshop.service;

import com.qf.ttshop.dto.MessageResult;

/**
 * User: DHC
 * Date: 2018/2/2
 * Time: 9:56
 * Version:V1.0
 */
public interface LoginService {
    MessageResult userLogin(String username, String password);
}
