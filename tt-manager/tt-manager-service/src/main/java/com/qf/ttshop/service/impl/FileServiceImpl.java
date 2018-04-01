package com.qf.ttshop.service.impl;

import com.qf.ttshop.service.FileService;
import com.qf.ttshop.utils.FtpUtils;
import com.qf.ttshop.utils.IDUtils;
import com.qf.ttshop.utils.Prop;
import com.qf.ttshop.utils.PropKit;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * User: DHC
 * Date: 2018/1/18
 * Time: 9:56
 * Version:V1.0
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public Map<String, Object> uploadImage(MultipartFile upfile) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //图片上传，上传到阿里云服务器
            Prop prop = PropKit.use("managerwebftp.properties");
            String host = prop.get("ftp.host");
            int port = prop.getInt("ftp.port");
            String username = prop.get("ftp.username");
            String password = prop.get("ftp.password");
            String basePath = prop.get("ftp.basePath");
            String filePath = new DateTime().toString("/yyyy/MM/dd");//"/2018/01/18"
            String name = upfile.getOriginalFilename();//"abc.jpg"
            String type = name.substring(name.lastIndexOf("."));
            String filename = IDUtils.genImageName() + type;
            //调用工具类进行上传
            boolean bl = FtpUtils.uploadFile(host, port, username, password, basePath, filePath, filename, upfile.getInputStream());
            //上传成功之后返回JSON
            if (bl) {
                map.clear();
                map.put("state", "SUCCESS");
                map.put("title", filename);
                map.put("original", name);
                map.put("type", type);
                map.put("url", filePath + "/" + filename);
                map.put("size", upfile.getSize());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
