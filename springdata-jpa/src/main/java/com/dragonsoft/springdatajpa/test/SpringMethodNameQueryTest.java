package com.dragonsoft.springdatajpa.test;

import com.dragonsoft.springdatajpa.domain.User;
import com.dragonsoft.springdatajpa.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试SpringData-JPA @Query中使用jpql查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
public class SpringMethodNameQueryTest {

    @Autowired
    private IUserService userService;

    /**
     * 方法命名规则的查询方式:精确查询
     * @return
     */
    @Test
    public void testFindByName(){
        User user = userService.findByName("张三");
        System.out.println(user);
    }

    /**
     * 方法命名规则的查询方式:模糊查询
     *      注意:%%要在调用时就加上
     * @return
     */
    @Test
    public void testFindByNameLike(){
        User user = userService.findByNameLike("%张%");
        System.out.println(user);
    }

    /**
     * 方法命名规则的查询方式:多条件查询
     *      注意:%%要在调用时就加上
     * @return
     */
    @Test
    public void testFindByNameLikeAndAge(){
        User user = userService.findByNameLikeAndAge("%张%",18);
        System.out.println(user);
    }


}
