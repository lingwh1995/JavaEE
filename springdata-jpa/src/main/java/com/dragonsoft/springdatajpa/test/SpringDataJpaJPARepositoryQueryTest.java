package com.dragonsoft.springdatajpa.test;

import com.dragonsoft.springdatajpa.domain.User;
import com.dragonsoft.springdatajpa.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 测试SpringData-JPA 使用JPARepository封装好的方法进行查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
public class SpringDataJpaJPARepositoryQueryTest {

    @Autowired
    private IUserService userService;

    /**
     * 查询操作
     *      getOne():底层调用 getReference(),延迟加载策略，不使用该对象不发送sql
     */
    @Test
    public void testGetOne(){
        User user = userService.getdOne("1");
        //System.out.println(user);
    }

    /**
     * 查询操作
     *      getdFindById():底层调用 find(),非延迟加载策略，一旦使用直接发sql
     */
    @Test
    @Transactional
    public void testFindById(){
        User user = userService.getdFindById("1");
        userService.getdFindById("1");
        //不使用该对象也要发sql
        //System.out.println(user);
    }

    /**
     * 更新操作和保存都是save()，根据又没有id区分这两种情况
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdate(){
        User user = userService.getdOne("1");
        user.setAge(98);
//        userService.update(user);
    }

    /**
     * 删除操作
     */
    @Test
    public void testDelete(){
        User user = userService.getdOne("1");
        userService.delete(user);
    }

    /**
     * 查询所有操作
     */
    @Test
    public void testFindAll(){
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 查询总记录数
     */
    @Test
    public void testCount(){
        Long count = userService.count();
        System.out.println(count);
    }

    /**
     * 根据id判断是否存在
     */
    @Test
    public void testIsExist(){
        boolean exist = userService.isExist("1");
        System.out.println(exist);
    }

    /**
     * 测试使用Specification封装多个查询条件
     */
    @Test
    public void testFindUserByUserSpecificationDealCondtition(){
        User user = new User();
        user.setName("a");
        user.setAge(18);
        List<User> users = userService.findUserByUserSpecificationDealCondtition(user);
        System.out.println(users);
    }

}
