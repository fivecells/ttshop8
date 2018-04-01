package com.qf.cxf2.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2018-01-31T15:01:27.033+08:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://service.cxf2.qf.com/", name = "WeatherService")
@XmlSeeAlso({ObjectFactory.class})
public interface WeatherService {

    @WebMethod
    @RequestWrapper(localName = "queryWeather", targetNamespace = "http://service.cxf2.qf.com/", className = "com.qf.cxf2.service.QueryWeather")
    @ResponseWrapper(localName = "queryWeatherResponse", targetNamespace = "http://service.cxf2.qf.com/", className = "com.qf.cxf2.service.QueryWeatherResponse")
    @WebResult(name = "return", targetNamespace = "")
    public String queryWeather(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0
    );
}