package com.dragonsoft.base.test;

import com.dragonsoft.base.domain.Car;
import com.dragonsoft.base.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {
    public static void main(String[] args) {
        /**
         * ApplicationContext        -- 在加载applicationContext.xml时候就会创建具体的Bean对象的实例，还提供了一些其他的功能
         */
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-base.xml");
        // 通过工厂获得类:
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.sayHello();

        /**
         * 构造方法注入
         */
        Car car_constructor = (Car) applicationContext.getBean("car_constructor");
        System.out.println(car_constructor);

        /**
         * 构造方法注入
         */
        Car car_constructor_index = (Car) applicationContext.getBean("car_constructor_index");
        System.out.println(car_constructor_index);

        /**
         * Setter注入
         */
        Car car_setter = (Car) applicationContext.getBean("car_setter");
        System.out.println(car_setter);

    }
}
