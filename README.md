# BrainFuckIDE
BrainFuckIDE<br></br>
<h3>一、简介：<br></br></h3>
1.	本IDE使用Client-Server模式，需要有客户端与服务器端。<br></br>
    客户端负责提供GUI界面，服务器端负责存取与执行代码。<br></br>
2.	实现BF代码的执行功能:<br></br>
    客户端将源码传到服务器端执行后，服务器端将运行结果返回客户端。<br></br>
3.	多用户操作:<br></br>
    登录、登出、添加用户、删除用户、修改用户。<br></br>
    每个用户只能访问自己创建的文件。<br></br>
4.  源码文件的历史版本保留功能：<br></br>
    (version菜单选项，打开/新建文件后才会有下拉菜单)可以将代码恢复到过去某一次保存后的状态。<br></br>
5.  界面说明：<br></br>
    中间为代码编辑区，左下为BF程序运行输入区，右下为BF程序输出区，最下为状态区，显示当前用户、文件、状态等。<br></br>
<br></br>
<h3>二、文件结构：<br></br></h3>
BF*/bin：程序所需要的运行库。<br></br>
BF*/src/service/：各种服务器提供的接口。<br></br>
BF*/src/rmi/：与服务器通信的RMI工具类。<br></br>
BF/src/runner/ClientRunner.java：BF客户端的主运行程序，启动类。<br></br>
BF/src/ui/：GUI文件。<br></br>
BFServer/src/runner/ClientRunner.java：BF服务器的主运行程序，启动类。<br></br>
BFServer/src/serviceImpl/：服务器服务的具体实现。<br></br>
<br></br>
<h3>三、其他<br></br></h3>
框架及创意提供：南京大学软件学院<br></br>
邮箱：liu@triumphal.cn<br></br>
