application.properties
生成SpringBoot项目的时候,配置文件默认在resource文件夹下,并且默认文件名为application.properties
1.可以在哪些地方创建配置文件(不论在哪里创建,对于SpringBoot来说全局配置文件必须名为application-xxx.yaml/properties)
· [优先级1]file:./config/ file就是这个工程目录,可以在工程目录下建立一个config文件夹,里面放置application-xxx.yaml/properties
· [优先级2]file:./        file就是这个工程目录,可以在工程目录下直接放置application-xxx.yaml/properties
· [优先级3]classpath:/config/ classpath就是类路径下,就是main文件夹里面的java和resource文件夹下创建config文件夹,里面放置application-xxx.yaml/properties
· [优先级4]classpath:/        classpath就是类路径下,就是main文件夹里面的java和resource文件夹下直接放置application-xxx.yaml/properties
· 也可以将项目打成jar包之后,在java -jar 运行命令中加入 配置文件的路径
2.功能:
· 可以快速的进行多环境切换,通过在更高优先级进行配置覆盖低优先级的配置
· 可以自己选择[springboot的多环境配置-可以选择激活哪个配置文件]
  - 假设现在有两个配置文件 application-xxx1.properties  application-xxx2.aproperties
  在默认的配置文件application-xxx1.properties中使用spring.profiles.active=xxx1,就可以指定激活xxx1后缀的配置文件
  - 使用yaml配置多环境一个yaml文件就可以搞定,在默认的application.yaml中将[不同的环境配置用"---"分割]
    例如：
    # 默认配置
    server:
      port: 8081
    spring:
      profiles:
        active:dev
    ---
    # 开发环境
    server:
      port: 8082
    spring:
      profiles: dev
    ---
    # 测试环境
    server:
      port: 8083
    spring:
      profiles: test
======>yaml优势明显
[测试要在main/java里的主类测试,而不是在test文件夹下测试]
error:application.yaml文件失效.
在application.yaml配置文件中,第一份配置
server:
  port: 8081
spring:
  profiles:
    active: dev
[active:后面忘记加空格导致失效!]

3.配置文件到底可以配置哪些东西 和 spring.factories有什么联系
3.1先找spring.factories EnableAutoConfiguration里面的spring.http.encodingAutoConfiguration进行分析
@Configuration 表示这是一个配置类 
@EnableConfigurationProperties(HttpProperties.class) 自动配置属性,和配置文件有些挂钩
 ctrl点击 查看HttpProperties是什么 
 [@ConfigurationProperties(prefix="spring.http") 这就是从yaml配置文件里加载配置的键形式!!!!]
 写个配置文件测试 敲入 spring.http. 看下有哪些东西可以配置
 可以配置的东西跟HttpProperties类里面的属性是对应起来的!
@ConditionalOnWebApplication 这是spring底层的注解,根据不同的条件(比如是否引入了包),来
 判断标注了@ConditionalOnWebApplication这个注解的配置类是否生效
 @ConditionalOnWebApplication(tyep = ConditionalOnWebApplication.Type.SERVLET)判断它是否是以一个web应用
 不是的话直接失效了
@ConditionalOnClass(CharacterEncodingFilter.class) 是否存在CharacterEncodingFilter这么一个类
 这个就是之前SpringMVC的字符编码过滤器
@ConditionalOnProperty(prefix="spring.http.encoding", value="enabled", matchIfMissing=true)判断
 配置文件spring.http.encoding的(因为Encoding是spring.http中的静态类)enable属性是否有设置,否则走默认值true
[3.2常用@ConditionalOnXxx系列注解见图片]
@ConditionalOnJava 系统的java版本是否符合要求
@ConditionalOnBean 容器中是否存在指定的bean
@ConditionalOnMissingBean 容器中是否不存在指定的bean
@ConditionalOnSingleCandidate 容器中是否只有一个指定的bean或者这个指定的bean是否是首选
@ConditionalOnClass 系统中是否有指定的类
@ConditionalOnMissingClass 系统中是否无指定的类
@ConditionalOnResource [类路径下]是否存在指定资源文件
@ConditionalOnProperty 系统中指定的属性是否有指定的值
@ConditionalOnWebApplication 当前是否是web环境
@ConditionalOnNotWebApplication 当前是否不是web环境
@ConditionalOnExpression 满足SpEL表达式指定
@ConditionalOnJndi JNDI存在指定项
3.3 找一个复杂的进行分析
想知道spring.mvc可以有哪些配置的话
在spring.factories里面找到WebMvcAutoConfiguration ctrl点进去
里面有个public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer
它被@EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class})所标注
[即绑定了{WebMvcProperties.class, ResourceProperties.class}这两个类]
然后ctrl点击{WebMvcProperties.class, ResourceProperties.class}进入即可看到
@ConfigurationProperties(prefix="spring.mvc")
public class WebMvcProperties{
即可查看可配置的所有属性   
3.4 共有的规律 都存在:
XxxAutoConfiguration — 装载 —> XxxProperties的配置类 —— 绑定 —— 配置文件application.yaml
这样就可以在application.yaml里面进行自定配置
[比如配置server.port的时候 直接ctrl点击port即可进入XxxProperties文件查看有哪些东西可以配置]
SprinBoot自动装配将XxxAutoConfiguration组件加载到容器中--->组件从XxxProperties里面取得每个属性的默认值
---->看自定义的application.yaml配置文件[绑定]了XxxProperties里面的哪些属性,读取覆盖默认值
3.5精髓[spring.factories在org.springframework.boot:spring-boot-autoconfigure:x.x.x.RELEASE的META-INF]
1)SpringBoot在启动的时候会[从spring.factories里面]加载大量的自动配置类XxxxAutoConfiguration
2)自动配置类XxxxAutoConfiguration加载组件的时候从XxxxProperties中加载默认值进行组件的设置
3)查看当前需求是否在SpringBoot默认自动配置XxxxAutoConfiguration类中,
  查看XxxxAutoConfiguration中配置了哪些组件(比如编码过滤等...可以使用@Component注解被扫描到然后使用的类)
4)给容器中自动配置类添加组件新值的时候, 会从组件对应的XxxxProperties类中获取某些属性,
  我们在配置文件中定义这些属性的值即可
XxxxAutoConfiguration 自动配置类:给容器添加组件(比如编码过滤等...可以使用@Component注解被扫描到然后使用的类)
XxxxProperties 封装组件的属性,并与全局配置文件application.yaml绑定,在其中可进行自定义设置
[DEBUG]-在全局配置文件中设置 [debug:true] 然后运行SpringBoot应用可以查看哪些[自动配置类]生效
因此在pom.xml中导入一个启动器或者模块之后,可以通过这中方式查看哪些自动配置类生效
Positive matches: 生效的类
Negative matches: 没生效的类
Exclusion: 第三类
没有生效的自动配置类在官网上找到对应的启动类 pom.xml导入依赖就可以生效了
