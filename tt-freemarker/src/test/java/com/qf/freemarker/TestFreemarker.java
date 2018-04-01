package com.qf.freemarker;

/**
 * User: DHC
 * Date: 2018/2/28
 * Time: 14:35
 * Version:V1.0
 */
/*public class TestFreemarker {

    @Test
    public void testFreemarker1() throws IOException, TemplateException {
        //创建一个Configuration对象。构造函数的参数就是对应的版本号
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板文件所在的路径
        configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
        //设置模板文件使用的字符集，使用UTF-8字符集
        configuration.setDefaultEncoding("UTF-8");
        //加载模板，创建模板对象
        Template template = configuration.getTemplate("01.ftl");
        //创建数据集，可以是POJO也可以是Map，多使用Map
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("name", "林志玲");
        dataModel.put("age", "18");
        //创建一个Writer对象，多创建FileWriter对象，并制定生成的文件名
        Writer writer = new FileWriter("d:/ftl/01.html");
        //调用模板对象的process方法输出文件
        template.process(dataModel, writer);
        //关闭流
        writer.close();
    }
}*/
