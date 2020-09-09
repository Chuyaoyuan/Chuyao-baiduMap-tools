# Chuyao-baiduMap-tools
百度地图经纬度和地址转换小工具（web）
(预留，码代码中。。。)

#基于Spring+SpringMVC+MyBatis+Maven+Bootstrap 3+Jsp(入门级)

注：文件批量上传支持Excel文件 xls（2003）格式

导入Eclips或IDEA即可，环境为JDK1.8+Tomcat 8，
Mysql配置在dbconfig.properties，修改成自己数据库的password，建表脚本在sql文件夹中，
在QueryController.java中查找'ak='，在其后添加自己的Baidumap Key，也可使用Baidu的（E4805d16520de693a3fe707cdc962045）,在maptools.jsp中查找'ak='，在其后添加自己的Baidumap Key，也可使用Baidu的（E4805d16520de693a3fe707cdc962045）

#演示地址：http://demo.chuyaoyuan.com/baidumaptool
用户名/密码：admin/123456

#测试数据

国内;
黑龙江省大庆市萨尔图区美兰街98号，
福建省龙岩市新罗区南环西路14号-1，
湖南省长沙市岳麓区金星北路一段517号，

国外;
Marsiling Ln, Goshen Departmental Store, Singapore ，
台湾省台北市長沙街一段2號

#部分截图（功能完善中）：

登陆
![login](https://raw.githubusercontent.com/Chuyaoyuan/Chuyao-baiduMap-tools/master/pic/login.png)
地址查询
![address](https://raw.githubusercontent.com/Chuyaoyuan/Chuyao-baiduMap-tools/master/pic/address.png)
经纬度查询
![lonlat](https://raw.githubusercontent.com/Chuyaoyuan/Chuyao-baiduMap-tools/master/pic/%E7%BB%8F%E7%BA%AC%E5%BA%A6.png)
批量查询
![long](https://raw.githubusercontent.com/Chuyaoyuan/Chuyao-baiduMap-tools/master/pic/file.png)
上传错误示例
![error](https://raw.githubusercontent.com/Chuyaoyuan/Chuyao-baiduMap-tools/master/pic/%E9%94%99%E8%AF%AF2.png)
xls文件格式
![error](https://raw.githubusercontent.com/Chuyaoyuan/Chuyao-baiduMap-tools/master/pic/%E6%96%87%E4%BB%B6.png)

over.
