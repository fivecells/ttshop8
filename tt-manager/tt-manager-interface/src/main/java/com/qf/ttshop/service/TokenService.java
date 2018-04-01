package com.qf.ttshop.service;

import com.qf.ttshop.dto.MessageResult;

/**
 * User: DHC
 * Date: 2018/2/2
 * Time: 15:03
 * Version:V1.0
 */
public interface TokenService {
    public MessageResult getUserByToken(String token);
}
