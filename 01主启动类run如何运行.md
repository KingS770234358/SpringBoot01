主启动类run如何运行
1.分析该方法主要分为两部分:
·SpringApplication的实例化
·run方法的执行
2.SpringApplication这个类主要做了以下四件事情
2.1 推断应用的类型是普通的项目 还是web项目(就是是否去配置springMVC和tomcat)
2.2 查找并加载所有可用初始化器, 设置到initializers属性中
2.3 找出所有的应用程序监听器, 设置到listeners属性中
2.4 推断并[设置main方法的定义类], 找到运行的主类
3.查看SpringApplication的原码
3.1构造函数
刚刚的2.1-2.4
3.2run方法(静态的static)中第一个参数的类 只要是使用@SpringBootApplication注解标注的类
也就是说,可以写一个不是SpringBootApplication的类专门负责启动,执行run方法
run的时候传入的类可以是不同的使用@SpringBootApplication注解标注的类就行
[启动类 启动 SpringBootApplication应用类] 
run方法的[又执行了一个run方法],返回值是ConfigurableApplicationContext
点击第二次执行的run方法查看,里面返回了new SpringApplication(xxxx).run()[又执行了run方法]
点击new SpringApplication(xxxx)查看 点击里面的this查看(回到构造函数)
[核心就是JavaConfig 两个注解@Configuration @Bean]

4.面试关于SpringBoot谈理解
· 自动装配
· run()方法(除非要重构,不然没什么意义)
主要说run的四个步骤(也可以[阅读网上关于run方法的文章])
4.1 推断应用的类型是普通的项目 还是web项目(就是是否去配置springMVC和tomcat)
4.2 推断并[设置main方法的定义类], 找到运行的主类
4.3 启动全局存在的监听器,获取上下文,处理容器中的Bean(bean的加载 初始化)
4.4 全面接管SpringMVC的配置(自动配置的不够用)

5.SpringBoot默认配置文件[详见application.properties]如何配置
Spring Initialize的时候Developer可以勾选工具