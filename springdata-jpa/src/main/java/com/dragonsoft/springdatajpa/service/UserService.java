package com.dragonsoft.springdatajpa.service;
import java.util.Map;
import	java.util.Optional;

import com.dragonsoft.springdatajpa.dao.IUserDao;
import com.dragonsoft.springdatajpa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Override
    public User getdOne(String id) {
        return userDao.getOne(id);
    }

    /**
     * 此方法需要再pom中配置编辑级别为1.8
     * @param id
     * @return
     */
    public User getdFindById(String id) {
        Optional<User> option = userDao.findById(id);
        User user = option.get();
        return user;
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Long count() {
        return userDao.count();
    }

    @Override
    public boolean isExist(String id) {
        return userDao.existsById(id);
    }

    /**
     * 根据username查询:使用JPQL
     * @param name
     * @return
     */
    @Override
    public User findByNameUseJPQL(String name) {
        return userDao.findByNameUseJPQL(name);
    }

    /**
     * 根据username查询:使用原生SQL
     * @param name
     * @return
     */
    @Override
    public User findByNameUseNativeSql(String name) {
        return userDao.findByNameUseNativeSql(name);
    }

    @Override
    public User findByIdAndNameUseJPQL(String id, String name) {
        return userDao.findByIdAndNameUseJPQL(id,name);
    }

    @Override
    public User findByNameUseJPQLUseNameParam(String name) {
        return userDao.findByNameUseJPQLUseNameParam(name);
    }

    @Override
    public Integer updateByIdUseJPQL(String id, String name) {
        return userDao.updateByIdUseJPQL(id,name);
    }

    @Override
    public List<User> findListUseNativeSql() {
        return userDao.findListUseNativeSql();
    }

    /**
     * 方法命名规则的查询方式:精确查询
     * @param name
     * @return
     */
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }


    /**
     * 方法命名规则的查询方式:模糊查询
     * @param name
     * @return
     */
    @Override
    public User findByNameLike(String name) {
        return userDao.findByNameLike(name);
    }


    /**
     * 模糊查询
     * @param name
     * @return
     */
    @Override
    public List<User> findByNameUseJPQLLike1(String name) {
        return userDao.findByNameUseJPQLLike1(name);
    }

    /**
     * 模糊查询
     * @param name
     * @return
     */
    @Override
    public List<User> findByNameUseJPQLLike2(String name) {
        return userDao.findByNameUseJPQLLike2(name);
    }

    @Override
    public User findByNameLikeAndAge(String name, Integer age) {
        return userDao.findByNameLikeAndAge(name,age);
    }

    @Override
    public List<User> findUserByUserSpecificationDealCondtition(User user) {
        return userDao.findAll(new Specification<User>() {
            /**
             * @param root 根对象,也就是要把条件封装到那个对象中,所有的输入参数可以从root中获取
             * @param criteriaQuery 封装的都是查询关键字,如group by ,order by等
             * @param criteriaBuilder 用来封装查询条件对象的,如果直接返回null,表示不需要任何对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //注意:一般拼接条件要之前要先判断时候传递过来了某个条件的值,如:if(user.getName().equals("")){......}else{......}
                Predicate name = criteriaBuilder.like(root.get("name").as(String.class), "%" + user.getName() + "%");//where name like '%小明%'
                Predicate age = criteriaBuilder.like(root.get("age").as(String.class),  user.getAge()+"");//where age = '18'
                return criteriaBuilder.and(name,age);
            }
        });
    }

    @Override
    public Page<User> paging1(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public Page<User> paging2(Pageable pageable) {
        return userDao.paging2(pageable);
    }

    @Override
    public Page<User> paging3(Pageable pageable) {
        return userDao.paging3(pageable);
    }

    @Override
    public Page<User> paging4(Pageable pageable) {
        Specification<User> specification = new Specification<User>() {
            /**
             * 构造断言
             * @param root 实体对象引用
             * @param query 规则查询对象
             * @param criteriaBuilder 规则构建对象
             * @return 断言
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate age = criteriaBuilder.equal(root.get("age").as(String.class),  "18");//where age = '18'
                return criteriaBuilder.and(age);
            }
        };
        return userDao.findAll(specification,pageable);
    }

    @Override
    public Page<User> paging5(Pageable pageable) {
        return userDao.findAll(new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate age = criteriaBuilder.equal(root.get("age").as(String.class),  "18");//where age = '18'
                return criteriaBuilder.and(age);
            }
        },pageable);
    }
}
