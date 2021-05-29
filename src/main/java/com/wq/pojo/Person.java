package com.wq.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 也要加上组件@Component注解才能放入Spring容器中
 * 要使用的时候使用@Autowired注入到对应的字段上
 * (如果有多个需要特别指定一个,使用@Qualifier("具体的bean"))即可
 *
 * 原来给属性赋值方式:
 * 1.在属性上或者属性对应的set方法上使用@Value("")注解
 * 2.在类上使用@ConfigurationProperties标签可以使用yaml赋值
 *   a.在没有配置的情况下加这个注解IDEA上方爆红,但是不影响运行,要根据所提示的文档进行设置后就不会爆红了。
 *   b.使用prefix=key的赋值方式,在springboot全局配置文件中一定要有相关的键key存在,如下所示
 *   c.要求key下面的每个子key一定要和这个Person类的属性一模一样,否则无法取到,将为空
 * */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birthday;
    private Map<String,Object> maps;
    private List<Object> things;
    private Dog dog;

    public Person() {
    }

    public Person(String name, Integer age, Boolean happy, Date birthday, Map<String, Object> maps, List<Object> things, Dog dog) {
        this.name = name;
        this.age = age;
        this.happy = happy;
        this.birthday = birthday;
        this.maps = maps;
        this.things = things;
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getHappy() {
        return happy;
    }

    public void setHappy(Boolean happy) {
        this.happy = happy;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getThings() {
        return things;
    }

    public void setThings(List<Object> things) {
        this.things = things;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", happy=" + happy +
                ", birthday=" + birthday +
                ", maps=" + maps +
                ", things=" + things +
                ", dog=" + dog +
                '}';
    }
}
