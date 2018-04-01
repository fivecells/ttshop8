package com.qf.ttshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * User: DHC
 * Date: 2018/1/18
 * Time: 9:55
 * Version:V1.0
 */
public interface FileService {
    Map<String, Object> uploadImage(MultipartFile upfile);
}
