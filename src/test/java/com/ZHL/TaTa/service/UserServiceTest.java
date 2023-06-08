package com.ZHL.TaTa.service;
import com.ZHL.TaTa.entity.User;
import com.ZHL.TaTa.mapper.UserMapper;
import com.ZHL.TaTa.service.ex.ServiceException;
import com.ZHL.TaTa.utils.Send;
import com.ZHL.TaTa.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void testReg(){
        try {
            User user = new User();
            user.setUsername("张十");
            user.setPassword("123456");
            userService.reg(user);
            log.info("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User sanuoa = userService.login("张十", "123456");
        System.out.println(sanuoa);

    }

    @Test
    public void updatePassword(){
        userService.ChangePassword(1638846052842893313L,"sansuoa","123456","123");
    }

    @Test
    public void getByUid() {
        try {
            Long id = 1638827459698757634L;
            User user = userService.getMessageById(id);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo() {
        try {
            Long uid = 1638827459698757634L;
            String username = "数据管理员";
            User user = new User();
            user.setPhone("15512328888");
            user.setEmail("admin03@cy.cn");
            user.setGender(2);
            userService.changeMessage(uid, username, user);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateAvatar(){
        userService.updateAvatarById(1638827459698757634L,"图片123.jpg");
    }

    @Test
    public void update(){
        Send.send("18649208896","1160");
    }
}