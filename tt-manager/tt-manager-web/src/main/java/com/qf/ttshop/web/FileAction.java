package com.qf.ttshop.web;

import com.qf.ttshop.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * User: DHC
 * Date: 2018/1/18
 * Time: 9:29
 * Version:V1.0
 */
@Controller
public class FileAction {

    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/file/upload", method = RequestMethod.GET)
    public void load(HttpServletRequest request, HttpServletResponse response) {
        //设置字符集
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("applicaton/json");
            String action = request.getParameter("action");
            if ("config".equals(action)) {
                //通过response获取输出流
                PrintWriter out = response.getWriter();
                //读取类路径下的配置文件 resources/config.json
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.json");
                //将inputStream写入到输出流中
                IOUtils.copy(inputStream, out, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam("upfile") MultipartFile upfile) {
        //这个方法的入参的变量名一定要是upfile，这个变量名称跟config.json当中的imageFieldName对应
        Map<String, Object> map = null;
        try {
            map = fileService.uploadImage(upfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
