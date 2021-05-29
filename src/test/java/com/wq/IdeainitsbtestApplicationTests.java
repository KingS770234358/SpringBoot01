package com.wq;

import com.wq.pojo.Dog;
import com.wq.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdeainitsbtestApplicationTests {

    @Autowired
    private Dog dog;
    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(person);

    }
}
