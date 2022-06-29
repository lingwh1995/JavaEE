package com.dragonsoft.springdatajpa.service;

import com.dragonsoft.springdatajpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface IUserService {
    User getdOne(String id);
    User getdFindById(String id);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    Long count();
    boolean isExist(String id);
    User findByNameUseJPQL(String name);

    /**
     * 模糊查询
     * @param name
     * @return
     */
    List<User> findByNameUseJPQLLike1(String name);
    List<User> findByNameUseJPQLLike2(String name);
    User findByNameUseNativeSql(String name);
    User findByIdAndNameUseJPQL(String id,String name);
    User findByNameUseJPQLUseNameParam(String name);
    Integer updateByIdUseJPQL(String id,String name);
    List<User> findListUseNativeSql();

    /**
     * 方法命名规则的查询方式:精确查询
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 方法命名规则的查询方式:模糊查询
     * @param name
     * @return
     */
    User findByNameLike(String name);

    /**
     * 方法命名规则的查询方式:多条件查询
     * @param name
     * @param age
     * @return
     */
    User findByNameLikeAndAge(String name,Integer age);

    /**
     * 使用Specification封装多个查询条件进行查询
     * @param user
     * @return
     */
    List<User> findUserByUserSpecificationDealCondtition(User user);

    /**
     * 分页:使用SpringDataJpa提供的API
     * @param pageable
     * @return
     */
    Page<User> paging1(Pageable pageable);

    /**
     * 分页:使用原生的SQL
     * @param pageable
     * @return
     */
    Page<User> paging2(Pageable pageable);

    /**
     * 分页:使用HQL
     * @param pageable
     * @return
     */
    Page<User> paging3(Pageable pageable);

    /**
     * 分页:使用HQL
     * @param pageable
     * @return
     */
    Page<User> paging4(Pageable pageable);

    /**
     * 分页:使用HQL
     * @param pageable
     * @return
     */
    Page<User> paging5(Pageable pageable);
}
