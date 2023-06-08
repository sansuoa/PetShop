package com.ZHL.TaTa.mapper;
import com.ZHL.TaTa.entity.User;
import com.ZHL.TaTa.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void test(){
        User user = new User();
        user.setUsername("张五");
                user.setPassword("123456");
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void updatePassword(){
        User user = new User();
        Long id = 1638818058896175106L;
        user.setId(id);
        user.setPassword("123456");
        userMapper.updateById(user);
    }

    @Test
    public void find(){
        User user = new User();
        Long id = 1638818058896175106L;
        user.setId(id);
        userMapper.selectById(id);
    }

    //这里测试更新信息
    @Test
    public void updateMessage(){
        User user = new User();
        user.setId(1638827459698757634L);
        user.setEmail("a13612069860@163.com");
        user.setPhone("18649208896");
        userMapper.updateById(user);
    }
}