package com.dragonsoft.annotation.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="T_ORDER")
public class Order {

    @Id
    @GeneratedValue(generator="hibernate-built-in")
    @GenericGenerator(name="hibernate-built-in",strategy="uuid")
    @Column(name="ID")
    private String id;

    @Column(name="PRICE")
    private Double price;

    @ManyToOne(targetEntity=Customer.class,cascade=CascadeType.ALL)
    @JoinColumn(name="CUSTOMER_ID")//外键
    private Customer customer;

    public Order() {
    }

    public Order(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", price=" + price +
                '}';
    }
}
