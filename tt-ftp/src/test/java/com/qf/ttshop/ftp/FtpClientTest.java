package com.qf.ttshop.ftp;

/**
 * User: DHC
 * Date: 2018/1/15
 * Time: 14:26
 * Version:V1.0
 */
/*public class FtpClientTest {
    *//*@Test
    public void testStoreFile() throws IOException {
        //创建FTP客户端
        FTPClient ftpClient = new FTPClient();
        //连接
        ftpClient.connect("101.132.38.253", 21);
        //登录
        ftpClient.login("ftpuser", "dhc890dhc");
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("d:/hi.jpg"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //上传图片文件
        ftpClient.storeFile("fengjing.jpg", fileInputStream);
        //释放资源
        fileInputStream.close();
        ftpClient.logout();
    }

    @Test
    public void testStoreFile2() throws FileNotFoundException {
        Prop prop = PropKit.use("ftp.properties");
        String host = prop.get("ftp.host");
        int port = prop.getInt("ftp.port");
        System.out.println(host + "+++++++++++" + port);
        *//**//*FileInputStream fileInputStream = new FileInputStream(new File("d:/hi.jpg"));
        boolean b = FtpUtils.uploadFile("101.132.38.253", 21, "ftpuser", "dhc890dhc", "/home/ftpuser/www/images", "/2018/01/15", "hahaha.jpg", fileInputStream);
        System.out.println("++++++++++++++++++++" + b);*//**//*
    }*//*

    @Test
    public void testJedis1(){
//        Jedis jedis = new Jedis("101.132.38.253", 6379);
//        System.out.println(jedis.get("name"));
        String name = "abcd.jpg";
        int i = name.lastIndexOf(".");
        name = name.substring(name.lastIndexOf("."));
        System.out.println(name+"==========="+i);
    }

}*/
