package com.dragonsoft.springdatajpa.dao;

import com.dragonsoft.springdatajpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 符合jpa规范的的接口:
 *      JpaRepository<操作的实体类型,实体中主键的类型>
 *          封装了基本的CRUD操作
 *      JpaSpecificationExecutor<操作的实体类型>
 *          封装了复杂查询操作，如分页
 */
@Repository
public interface IUserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    /**
     * 注意:?后面要加1，代表的是该值是方法中第一个实参的值
     * @param name
     * @return
     */
    @Query(value="from User where name = ?1")
    User findByNameUseJPQL(String name);

    /**
     * 使用命名参数传递参数
     * @param name
     * @return
     */
    @Query(value="from User where name = :name")
    User findByNameUseJPQLUseNameParam(@Param("name") String name);


    /**
     * 注意:?后面要加1，代表的是该值是方法中第一个实参的值
     * @param id
     * @param name
     * @return
     */
    @Query(value="from User where id = ?1 and name = ?2")
    User findByIdAndNameUseJPQL(String id,String name);

    /**
     * 使用原生Sql查询
     * @param name
     * @return
     */
    @Query(value="select * from T_User where name = ?", nativeQuery=true)
    User findByNameUseNativeSql(String name);

    /**
     * 使用原生Sql查询
     * @return
     */
    @Query(value="select * from T_User", nativeQuery=true)
    List<User> findListUseNativeSql();

    /**
     * 更新操作：根据id更新姓名,@Modifying:代表是更新方法
     * @param id
     * @param name
     * @return
     */
    @Modifying
    @Query(value="update User set name = ?2 where id = ?1")
    Integer updateByIdUseJPQL(String id,String name);

    /**
     * 方法名称约定查询:精确查询
     * find+属性名(首字母大写)，如根据name属性值查询:find + Name
     *      注意:此种情况下并不需要通过  @Query注解书写JPQL或者原生SQL,操作数据库更为简单
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 方法名称约定查询:模糊查询
     * find+属性名(首字母大写)，如根据name属性值查询:find + Name + Like
     *      注意:此种情况下并不需要通过  @Query注解书写JPQL或者原生SQL,操作数据库更为简单
     * @param name
     * @return
     */
    User findByNameLike(String name);

    /**
     * 方法名称约定查询:多条件
     * find+属性名(首字母大写)，如根据name和age属性值查询:find + Name + Like + AND + Age
     *      注意:此种情况下并不需要通过  @Query注解书写JPQL或者原生SQL,操作数据库更为简单
     * @param name
     * @param name
     * @return
     */
    User findByNameLikeAndAge(String name,Integer age);

    /**
     * 模糊查询
     * @param name
     * @return
     */
    @Query(value = "from User where name like ?1")
    List<User> findByNameUseJPQLLike1 (String name);

    /**
     * 模糊查询
     * @param name
     * @return
     */
    @Query(value = "from User where name like %?1%")
    List<User> findByNameUseJPQLLike2 (String name);


    /**
     * 分页查询SpringDataJPA:原生sql
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM T_USER",
            countQuery = "SELECT COUNT(*) FROM T_USER",
            nativeQuery = true)
    Page<User> paging2(Pageable pageable);

    /**
     * 分页查询SpringDataJPA:HQL
     * @param pageable
     * @return
     */
    @Query(value = "select u.id,u.name,u.age,u.gender from User u")
    Page<User> paging3(Pageable pageable);
}
