package com.ZHL.TaTa;

import com.ZHL.TaTa.entity.User;
import com.ZHL.TaTa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: 张宏利
 * @version: 1.0
 */
@SpringBootTest
public class ApplicationContest {


    @Autowired(required = false)
    private DataSource dataSource;

    @Test
    void contest() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
