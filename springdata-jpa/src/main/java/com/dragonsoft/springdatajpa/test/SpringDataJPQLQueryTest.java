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
 * 测试SpringData-JPA @Query中使用jpql查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
public class SpringDataJPQLQueryTest {

    @Autowired
    private IUserService userService;

    /**
     * 查询：一个参数
     */
    @Test
    public void testFindByUsername(){
        User user = userService.findByNameUseJPQL("张三1");
        System.out.println(user);
    }

    /**
     * like查询：在参数处就加%%等
     */
    @Test
    public void testFindByUsernameLike1(){
        List<User> userList = userService.findByNameUseJPQLLike1("%张%");
        for (User user : userList) {
            System.out.println(user);
        }
    }
    /**
     * like查询：在@Query中加%%
     */
    @Test
    public void testFindByUsernameLike2(){
        List<User> userList = userService.findByNameUseJPQLLike2("张");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 查询:两个参数
     */
    @Test
    public void testFindByIdUsername(){
        User user = userService.findByIdAndNameUseJPQL("1","张三");
        System.out.println(user);
    }

    /**
     * 查询:一个参数，使用命名参数@Param传递参数
     */
    @Test
    public void findByNameUseJPQLUseNameParam(){
        User user = userService.findByNameUseJPQLUseNameParam("张三");
        System.out.println(user);
    }

    /**
     * 更新，根据id更新name
     */
    @Test
    public void updateByIdUseJPQL(){
        Integer result = userService.updateByIdUseJPQL("1","张三1");
        System.out.println(result);
    }

}
