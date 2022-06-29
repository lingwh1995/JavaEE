package com.dragonsoft.springdatajpa.test;

import com.dragonsoft.springdatajpa.domain.User;
import com.dragonsoft.springdatajpa.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 测试SpringData-JPA @Query中使用原生sql查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
public class SpringDataJNativeSqlQueryTest {

    @Autowired
    private IUserService userService;

    /**
     * 查询操作
     *      getOne():底层调用 getReference(),延迟加载策略，不使用该对象不发送sql
     */
    @Test
    public void testFindByUsername(){
        User user = userService.findByNameUseNativeSql("张三");
        System.out.println(user);
    }

    /**
     * 查询所有操作
     *      getOne():底层调用 getReference(),延迟加载策略，不使用该对象不发送sql
     */
    @Test
    public void testFindList(){
        List<User> userList = userService.findListUseNativeSql();
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
