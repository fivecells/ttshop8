package com.qf.cxf2.action;

import com.qf.cxf2.service.WeatherService;

import javax.annotation.Resource;

/**
 * User: DHC
 * Date: 2018/1/31
 * Time: 15:08
 * Version:V1.0
 */
public class WeatherAction {
    @Resource
    private WeatherService weatherService;
}
