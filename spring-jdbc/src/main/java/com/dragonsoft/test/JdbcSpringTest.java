package com.dragonsoft.test;
import	java.util.HashMap;

import com.dragonsoft.example.domain.Account;
import com.labrador.persistence.IdUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcSpringTest {

    @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**初始化数据*/
    @Test
    public void init(){
        jdbcTemplate.update("INSERT INTO T_ACCOUNT VALUES (?,?,?)", IdUtils.getRandomIdByUUID(),"张三",1);
        jdbcTemplate.update("INSERT INTO T_ACCOUNT VALUES (?,?,?)", IdUtils.getRandomIdByUUID(),"李四",2);
        jdbcTemplate.update("INSERT INTO T_ACCOUNT VALUES (?,?,?)", IdUtils.getRandomIdByUUID(),"张三",3);
        jdbcTemplate.update("INSERT INTO T_ACCOUNT VALUES (?,?,?)", IdUtils.getRandomIdByUUID(),"王五",4);
        jdbcTemplate.update("INSERT INTO T_ACCOUNT VALUES (?,?,?)", IdUtils.getRandomIdByUUID(),"赵六",5);
    }

    /**插入操作*/
    @Test
    public void fun1(){
        jdbcTemplate.update("INSERT INTO T_ACCOUNT VALUES (?,?,?)", IdUtils.getRandomIdByUUID(),"冠希",10000);
    }

    /**修改操作*/
    @Test
    public void fun2(){
        jdbcTemplate.update("UPDATE T_ACCOUNT SET NAME=?,MONEY =? WHERE ID = ?", "思雨",20000,"9d49945f8f2d4f6b833944b6f04b1078");
    }

    /**删除操作*/
    @Test
    public void fun3(){
        jdbcTemplate.update("DELETE FROM T_ACCOUNT WHERE ID = ?", "9d49945f8f2d4f6b833944b6f04b1078");
    }

    /**查询一条记录*/
    @Test
    public void fun4(){
        System.out.println("---------------------------------------------------------");
        Account accountByBeanMapper = jdbcTemplate.queryForObject("SELECT * FROM T_ACCOUNT WHERE ID = ?", new BeanMapper(), "00001");
        System.out.println("BeanMapper方式:"+accountByBeanMapper);
        System.out.println("---------------------------------------------------------");
        RowMapper<Account> accountRowMapper = new BeanPropertyRowMapper<>(Account.class);
        Account accountByRowMapper = jdbcTemplate.queryForObject("SELECT * FROM T_ACCOUNT WHERE ID = ?", accountRowMapper, "00001");
        //Account accountByRowMapper = jdbcTemplate.queryForObject("SELECT * FROM T_ACCOUNT WHERE ID = ?", accountRowMapper, new Object[]{"00001"});
        System.out.println("RowMapper方式:"+accountByRowMapper);
        System.out.println("---------------------------------------------------------");
    }

    /**
     * 查询所有记录，使用BeanMapper将单条数据封装到实体中
     */
    @Test
    public void fun5(){
        List<Account> list = jdbcTemplate.query("SELECT * FROM T_ACCOUNT", new BeanMapper());
        for (Account account : list) {
            System.out.println(account);
        }
    }

    /**
     * 查询所有记录，使用BeanMapper将单条数据封装到Map中
     */
    @Test
    public void fun6(){
        List<Map<String,String>> list = jdbcTemplate.query("SELECT * FROM T_ACCOUNT", new BeanMapperMap());
        for (Map<String,String> map : list) {
            System.out.println(map);
        }
    }

    /**
     * 查询所有的姓名，并把查询结果封装到一个List<String>中
     * 使用SingleColumnRowMapper封装数据
     */
    @Test
    public void fun7(){
        String sql = "SELECT NAME FROM T_ACCOUNT";
        List<String> names = jdbcTemplate.query(sql, new SingleColumnRowMapper<String>());
        System.out.println(names);
    }

}

/**
 * 把单条查询结果封装到实体中
 */
class BeanMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int arg1) throws SQLException {
        Account account = new Account();
        account.setId(rs.getString("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getDouble("money"));
        return account;
    }
}

/**
 * 把单条查询结果封装到Map中
 */
class BeanMapperMap implements RowMapper<Map<String,String>> {
    @Nullable
    @Override
    public Map<String, String> mapRow(ResultSet rs, int i) throws SQLException {
        HashMap hashMap = new HashMap<String, String>();
        hashMap.put("id",rs.getString("id"));
        hashMap.put("money",rs.getString("money"));
        hashMap.put("name",rs.getString("name"));
        return hashMap;
    }
}