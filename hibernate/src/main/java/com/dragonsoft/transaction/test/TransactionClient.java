package com.dragonsoft.transaction.test;

import com.dragonsoft.transaction.domain.User;
import com.dragonsoft.transaction.service.UserService;

public class TransactionClient {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setName("测试1");

        User u2 = new User();
        u2.setName("测试2");

        new UserService().save(u1, u2);
    }
}
