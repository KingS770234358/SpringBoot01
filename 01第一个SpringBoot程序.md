第一个SpringBoot程序
1.环境
·jdk1.8
·maven3.6.1
·SpringBoot:最新版
·IDE:IDEA

2.快速开发(官方提供快速生成的网站,IDEA集成了这个网站)
Spring官网->Project->SpringBoot->learn/quickstart
最新稳定版本标记(GA) 标记SNAP快照版本不建议使用
2.1 体验官方
点击overview中quick start 的 initialize项目(dependency搜spring web)后生成压缩包 解压 用IDEA import project导入
2.2IDEA集成了官方生成项目的功能,直接在IDEA中spring initializer创建项目即可 
2.2.1创建后可以删除的文件.mvn .gitignore HELP.md mvnw mvnw.cmd 
2.2.2默认生成
java下有个默认的包,里面有默认的SpringBoot程序可以直接运行(程序主入口) 
    点进注解@SpringBootApplication源码 发现他就是一个Spring的@Component注解
resource下有默认的application.properties应用配置文件(SpringBoot核心配置文件 推荐用.yaml格式)
test测试目录下有默认的单元测试类
[核心pom.xml配置文件] 它有一个父项目
<!-- 它有一个父项目 远程在线 relativePath为空 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.4.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
<!-- 项目的元数据信息、坐标 -->    
<groupId>com.wq</groupId>
<artifactId>officeInitialSpringBot</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>officeInitialSpringBot</name>
<!-- 项目的描述信息 -->  
<description>Demo project for Spring Boot</description>
<!-- jdk的版本 -->
<properties>
    <java.version>11</java.version>
</properties>
<!-- 各种依赖 -->
<dependencies>
    <!-- 所有的SpringBoot依赖都是以spring-boot-starter-打头的 -->
    <!-- web依赖:集成tomcat、配置DispatcherServlet web等.xml配置 
         spring-boot-starter-web用于实现http接口(包含springMVC)
         使用springMVC构建web(包括RestFul)应用程序,使用Tomcat作为默认嵌入式容器
         
    -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- SpringBoot的单元测试依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
<!-- SpringBoot打jar包插件 配合spring-boot-starter-parent就可以把SpringBoot应用打成jar包运行 
     在IDEA左侧maven 项目的Lifecycle生命周期中双击package打包,在target目录下生成jar包(测试成功)
     这个jar包就是程序接口(实验室的金融项目也是微服务的思想)
     可以在命令行中执行这个jar包,然后在浏览器中做相同的访问
     · 命令行执行jar包命令 java -jar xx.jar
     · 注意1:一定要让出8080端口
     · 注意2:一定要使用正确的jdk版本 项目创建时,设定使用jdk11 就要调至IDEA的jkd
-->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>

这里跟随视频创建了一个项目com.wq.officeInitialSpringBot.OfficeInitialSpringBotApplication.java
进行自定义的部分要在com.wq.officeInitialSpringBot下建立pojo mapper service controller 等等的包
核心是与controller打交道,在controller里写一个控制器

3.使用IDEA创建SpringBoot项目
3.1选择maven上面的spring initializer - >choose initializer service url直接选择默认的 从官网上去下载
Artifact的名字得全部为小写才行...
这个用IDEA创建SpringBoot项目 不勾选web的话 就不会导入这个依赖
会导入一个spring-boot-starter启动器(之后讲解)
package设置的时候建议只保留两级 com.wq
删掉不需要的文件/夹
3.2在resource文件夹下默认的application.properties文件中修改tomcat的服务端口号
2020-01-29 16:13:24.239  INFO 11876 --- [           
main] o.s.b.w.embedded.tomcat.TomcatWebServer  : 
Tomcat started on port(s): 8081 (http) with context path ''
3.3 彩蛋 修改SpringBoot地banner,在resource目录下创建banner.txt,
[当右下角出现灰色原型标志]那是SpringBoot的标志,说明被SpringBoot接管,设置成功
banner设置网站:bootschool.net/ascii