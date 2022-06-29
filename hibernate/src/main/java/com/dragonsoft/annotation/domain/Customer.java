package com.dragonsoft.annotation.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 一个顾客对应多个订单
 */
@Entity
@Table(name="T_CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(generator="hibernate-built-in")
    @GenericGenerator(name="hibernate-built-in",strategy="uuid")
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="AGE")
    private Integer age;
    /**
     * Hibernate框架默认的集合是set集合，集合必须要自己手动的初始化
     * mappedBy:一方放弃维护管理关系的权力，将维护权交给多方
     *      注意:配置了mappedBy，再配置@JoinColumn(name="CUSTOMER_ID")就会报错，要删掉这个，代码就能正常运行
     */
    @OneToMany(targetEntity=Order.class,cascade=CascadeType.ALL,mappedBy="customer")
    //@JoinColumn(name="CUSTOMER_ID")
    private Set<Order> orders = new HashSet<Order>();

    public Customer() {

    }

    public Customer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Customer(String id,String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", orders=" + orders +
                '}';
    }
}
