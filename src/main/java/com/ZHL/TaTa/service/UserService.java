package com.ZHL.TaTa.service;

import com.ZHL.TaTa.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UserService extends IService<User> {
    void reg(User user);

    User login(String username,String password);

    //修改密码
    void ChangePassword(Long id,String username,String oldPassword,String newPassword);

    User getMessageById(Long id);

    void changeMessage(Long id, String username, User user);

    //上传头像
    void updateAvatarById(Long id,String avatar);

    String changePasswordByPhone(String password,String salt);
}
