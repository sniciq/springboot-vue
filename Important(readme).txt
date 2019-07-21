#springboot
1. 在IDE中以maven形式打开项目，SpringBoot集成的框架比较多，第一次打开时需要下载各种库，需要些时间，请耐心等待；

2. 如果前端使用的是ExtJS6，从AC下载的代码中不包含extjs6的库，可以从如下地址下载：https://yun.baidu.com/s/1o8vrR3G
    包含如下资料：    
    1. ext-6.2.0-gpl.zip ==> ExtJS6.2
    2. ext-6.2.0-gpl-ac.zip ==> AC示例使用的ExtJS6.2包，与ext-6.2.0-gpl.zip的差别在于：删除了没用到的主题，并增加了AC自定义的主题。
    3. extjs-docs-6-2 ==> ExtJS6.2 API文档
    4. material-1.1.1.zip ==> angular material 1.1.1版本
    
    springboot项目的静态文件位于：XXX-web/src/main/resources/static目录下，需要将下载到的ExtJS6框架复制到该目录下，且文件名必须是extjs6
    (正常的extjs6目录下应该包括：build文件夹、ext-bootstrap.js、version.properties)

3. 如果前端使用的是Angular、bootstrap、Material，从AC下载的代码中已经包含所有的框架代码；

4. 项目配置，配置文件位于：XXX-web/src/main/java/com/demo/bootstrap/web/config下
    1. 数据库配置：DataSourceConfig
    2. 多数据库时，需要动态修改MybatisConfig
    3. 数据库初始化脚本：doc/init.sql

5. 应用启动类在：XXX-web/src/main/java/com/demo/bootstrap/web/Application.java

6. 正式环境部署
    cd 项目根目录
    mvn clean install '-Dmaven.test.skip=true'
    java -jar web-1.0.0-SNAPSHOT.war

    web-1.0.0-SNAPSHOT.war在web模块下的target中，注意名称为"项目名-web.XXXX.war"

    多环境配置：
    删除将application.properties
    增加application-dev.properties、application-pro.properties
    application-dev.properties 用于开发配置
    application-pro.properties 用于生产环境配置
    在启动中增加参数：-Dspring.profiles.active=dev、-Dspring.profiles.active=pro
    如IntelliJ，在VM options中，配置为：-Dspring.profiles.active=dev即为使用开发配置
    也可以通过mvn配置，自行搜索哈！

    正式环境启动命令参考如下（更多启动参数请自行搜索）：
    nohup java -Dspring.profiles.active=pro -Dfile.encoding=UTF-8 -Xms256m -Xmx496m -jar web-1.0.0-SNAPSHOT.war > /dev/null 2>&1&

6. 时间关系，就写这么多了，祝你系统大成！
    如果您觉得AC对你有帮助，请登录AC后，在系统首页打赏我们，感谢！

    
    
    


