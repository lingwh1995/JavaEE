package com.dragonsoft.test;

import com.labrador.persistence.IdUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplement测试......
 */
public class DbcpTest {
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@192.168.0.123:1521:orcl");
        dataSource.setUsername("scott");
        dataSource.setPassword("tiger");
        // 创建JdbcTemplate
        jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println(jdbcTemplate);
        System.out.println("-----------------------------");
    }

    @Test
    public void run1(){
        // 完成数据的添加
        try {
            int update = jdbcTemplate.update("INSERT INTO T_ACCOUNT VALUES (?,?,?)", new Object[]{IdUtils.getRandomIdByUUID(), "测试", 10000});
            System.out.println("受影响的行数:"+update);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
