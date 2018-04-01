package com.qf.ttshop.item.web;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: DHC
 * Date: 2018/2/28
 * Time: 15:39
 * Version:V1.0
 */
@Controller
public class GenerateHtmlAction {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    //http://localhost:8085/ttshop/genHtml
    @ResponseBody
    @RequestMapping(value = "/genHtml",method = RequestMethod.GET)
    public String genHtml(){
        freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = null;
        try {
            template = configuration.getTemplate("02.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("name", "林志玲");
        Writer out = null;
        try {
            out = new FileWriter("d:/ftl/02.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            template.process(dataModel,out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "yes";
    }

}
