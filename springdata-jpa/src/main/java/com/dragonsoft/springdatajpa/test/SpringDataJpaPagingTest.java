package com.dragonsoft.springdatajpa.test;

import com.dragonsoft.springdatajpa.domain.User;
import com.dragonsoft.springdatajpa.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 测试SpringData-JPA分页
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
public class SpringDataJpaPagingTest {

    @Autowired
    private IUserService userService;

    /**
     * 使用功能SpringDataJPA提供的API完成操作，适用于简单的分页查询
     * 查询操作:
     *      注意:pageNum是从0开始,不是从1开始
     *      PageRequest.of(int pageNum, int pageSize);
     */
    @Test
    public void testPaging1(){
        Pageable page = PageRequest.of(0, 1);
        Page<User> users = userService.paging1(page);
        System.out.println(users.toString());
    }

    /**
     * 使用功能原生的SQL完成分页
     */
    @Test
    public void testPaging2(){
        Pageable page = PageRequest.of(0, 1);
        Page<User> users = userService.paging2(page);
        System.out.println(users.toString());
    }

    /**
     * 使用功能原生的HQL完成分页
     */
    @Test
    public void testPaging3(){
        Pageable page = PageRequest.of(0, 1);
        Page<User> users = userService.paging3(page);
        System.out.println(users.toString());
    }

    /**
     * 使用Specification拼接条件实现分页写法1
     */
    @Test
    public void testPaging4Specification_1(){
        Pageable page = PageRequest.of(0, 1);
        Page<User> users = userService.paging4(page);
        System.out.println(users.toString());
    }

    /**
     * 使用Specification拼接条件实现分页拼接条件写法2
     */
    @Test
    public void testPaging4Specification_2(){
        Pageable page = PageRequest.of(0, 1);
        Page<User> users = userService.paging5(page);
        System.out.println(users.toString());
    }
}
