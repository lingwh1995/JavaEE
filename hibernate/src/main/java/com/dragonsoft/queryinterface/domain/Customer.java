package com.dragonsoft.queryinterface.domain;


import java.util.HashSet;
import java.util.Set;

/**
 * 一个顾客对应多个订单
 */
public class Customer {
    private String id;
    private String name;
    private Integer age;
    /**
     * Hibernate框架默认的集合是set集合，集合必须要自己手动的初始化
     */
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
