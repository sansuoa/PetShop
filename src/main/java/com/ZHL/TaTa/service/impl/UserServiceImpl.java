package com.ZHL.TaTa.service.impl;

import com.ZHL.TaTa.entity.User;
import com.ZHL.TaTa.mapper.UserMapper;
import com.ZHL.TaTa.service.UserService;
import com.ZHL.TaTa.service.ex.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //这是注册功能
    @Override
    public void reg(User user) {
        String username = user.getUsername();
        LambdaQueryWrapper<User> queryWrapper
                = new LambdaQueryWrapper<>();
        queryWrapper.eq(username!=null,User::getUsername,username);
        User one = this.getOne(queryWrapper);
        if (one!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        String oldpassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String MD5password = getMD5password(oldpassword, salt);
        user.setPassword(MD5password);
        user.setSalt(salt);
        user.setIsDelete(0);
        boolean save = this.save(user);
        //这俩有mata自动填充所以没用了，以后再试试
//        user.setCreateUser(user.getUsername());
//        user.setUpdateUser(user.getUsername());
        if(save!=true){
            throw new InsertException("用户注册时产生未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper
                = new LambdaQueryWrapper<>();
        queryWrapper.eq(username!=null,User::getUsername,username);
        User one = this.getOne(queryWrapper);
        //这里看用户名在不在
        if (one==null){
            throw new UserNotFoundException("用户不存在");
        }
        String salt = one.getSalt();
        String md5newpassword = getMD5password(password, salt);
        //看密码匹不匹配
        if(!md5newpassword.equals(one.getPassword())){
            throw new PasswordNotMatchException("密码不匹配");
        }
        //看被没被删除
        if (one.getIsDelete() == 1){
            throw new UserNotFoundException("用户状态为禁用");
        }
        return one;
    }

    @Override
    public void ChangePassword(Long id, String username, String oldPassword, String newPassword) {
        User byId = this.getById(id);
        //检查用户是否存在
        if (byId == null || byId.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检查输入密码是否一样
        String md5password = getMD5password(oldPassword, byId.getSalt());
        if (!md5password.equals(byId.getPassword())){
            throw new PasswordNotMatchException("密码错误");
        }
        String md5password1 = getMD5password(newPassword, byId.getSalt());
        byId.setPassword(md5password1);
        boolean b = this.updateById(byId);
        if (b!=true){
            throw new UpdateException("更改时出现未知错误");
        }
    }

    @Override
    public User getMessageById(Long id) {
        User byId = this.getById(id);
        if(byId == null||byId.getIsDelete()==1){
            throw new UserNotFoundException("找不到用户数据");
        }
        return byId;
    }

    //这是更改资料，注意username不能更改
    @Override
    public void changeMessage(Long id, String username, User user) {
        User messageById = this.getMessageById(id);
        user.setId(id);
        boolean b = this.updateById(user);
        if ( b != true){
            throw new UpdateException("出现未知错误");
        }
    }

    @Override
    public void updateAvatarById(@Param("id") Long id,@Param("avatar") String avatar) {
        User messageById = this.getMessageById(id);
        messageById.setId(id);
        messageById.setAvatar(avatar);
        boolean b = this.updateById(messageById);
        if (b!=true){
            throw new UpdateException("更新时出现未知错误");
        }
    }

    @Override
    public String changePasswordByPhone(String password,String salt) {
        String md5password = getMD5password(password, salt);
        return md5password;
    }


    private String getMD5password(String password,String salt){
        password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        return password;
    }
}
