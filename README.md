# Food-identify
项目详细介绍：
https://www.cnblogs.com/yuanhang110/p/11229358.html  （介绍在我的博客）

想看运行效果可以下载运行视频

# 简介
自治区级创业训练计划项目：食途-扫描识别你的美食卡路里 
我是负责人
我负责的部分：

➢ 负责统筹规划项目安排，完善组内分工，用 JAVA 开发程序，实现调用 API 和 JAVA 界面的代码；

➢ 调用百度云平台的菜品识别 API 以实现食物识别，将图像上传，将返回的json格式的数据进行处理，将返回的菜名、卡路里、置信度信息赋值到控件上。

➢ 参与设计产品功能、绘制产品原型图，完成软件界面的实现，撰写项目结项书，申请计算机软件著作权。

项目界面：
![image](https://github.com/yuanhang110/Food-identify/blob/master/食途界面.png)

项目实现的功能模块如上图：

1.图像识别

2.识别历史纪录查询

3.检索数据库中菜品

使用平台Windows，已打包成exe，可直接运行，通过调用百度智能云平台来实现图像识别，不过api提供的accesstoken是30天更新一次，下载后替换成自己的就可以了。

数据库使用的是mysql，用navicat来进行管理

调用了阿里处理json数据的fastjson解析 jar包。使用了一些常见的布局，如网格布局

图像识别可以显示的结果如图：

![image](https://github.com/yuanhang110/Food-identify/blob/master/识别结果：红烧肉.png)

识别流程图：

![image](https://github.com/yuanhang110/Food-identify/blob/master/食物识别流程图.PNG)
