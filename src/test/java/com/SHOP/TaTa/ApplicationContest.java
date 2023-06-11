package com.SHOP.TaTa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class ApplicationContest {


    @Autowired(required = false)
    private DataSource dataSource;

    @Test
    void contest() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
