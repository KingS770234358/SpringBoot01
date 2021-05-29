SpringBoot Web 开发
jar -- > webapp
SpringBoot最大特点:自动装配
·SpringBoot应用步骤
1.创建应用
2.选择模块,在pom.xml中导入对应的启动器依赖
3.XxxAutoConfiguration根据[ConditionalOnXxxx]装配组件(从XxxxProperties类中读取默认值)
  从全局配置文件application.yaml中读取XxxxProperties类绑定的值 覆盖默认值

· 思考
1.SpringBoot自动配置了哪些东西
2.是否可以进行修改
3.如何修改
4.是否可以扩展
org.springframework.boot:spring-boot-Autoconfiguration下
META-INFO里的spring.factories中有许多1XxxAutoConfiguration

· 要解决的问题
1.导入静态资源 js...
2.首页
3.jsp? 模板引擎:Thymeleaf
4.装配扩展SpringMVC
5.增删改查
6.拦截器
7.国际化