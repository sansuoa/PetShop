package com.ZHL.TaTa.controller;

import com.ZHL.TaTa.common.R;
import com.ZHL.TaTa.controller.ex.*;
import com.ZHL.TaTa.entity.Employee;
import com.ZHL.TaTa.entity.PetSale;
import com.ZHL.TaTa.entity.User;
import com.ZHL.TaTa.service.UserService;
import com.ZHL.TaTa.service.ex.InsertException;
import com.ZHL.TaTa.service.ex.PhoneNotFindException;
import com.ZHL.TaTa.service.ex.UserNotFoundException;
import com.ZHL.TaTa.service.ex.UsernameDuplicatedException;
import com.ZHL.TaTa.service.impl.UserServiceImpl;
import com.ZHL.TaTa.utils.JsonResult;
import com.ZHL.TaTa.utils.Send;
import com.ZHL.TaTa.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    //这也是员工表的
    @PutMapping
    public R<String> update(HttpServletRequest httpServletRequest,@RequestBody User user){
        log.info(user.toString());
        long id = Thread.currentThread().getId();
        log.info("线程id为:{}",id);
        //employee.setUpdateTime(LocalDateTime.now());
        //Long employee1 = (Long) httpServletRequest.getSession().getAttribute("employee");
        //employee.setUpdateUser(employee1);
        userService.updateById(user);
        return R.success("用户信息已修改");
    }
    //把员工表的抄过来改改
    @PutMapping("/password")
    public R<String> updateall(@RequestBody User user){
        log.info(user.toString());
        long id = Thread.currentThread().getId();
        user.setPassword(DigestUtils.md5DigestAsHex((user.getSalt()+user.getPassword()+user.getSalt()).getBytes()).toUpperCase());
        log.info("线程id为:{}",id);
        //employee.setUpdateTime(LocalDateTime.now());
        //Long employee1 = (Long) httpServletRequest.getSession().getAttribute("employee");
        //employee.setUpdateUser(employee1);
        userService.updateById(user);
        return R.success("用户信息已修改");
    }
    //这个是修改时返回要修改的数据
    @GetMapping("/{id}")
    public R<User> get(@PathVariable Long id){
        User byId = userService.getById(id);
        return R.success(byId);
    }

    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
        // 调用业务对象执行注册
        userService.reg(user);
        // 返回
        return new JsonResult<Void>(OK);
    }

    //员工信息分页查询
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pagesize={},name={}",page,pageSize,name);
        Page objectPage = new Page(page, pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),User::getUsername,name);
        lambdaQueryWrapper.orderByDesc(User::getUpdateTime);
        userService.page(objectPage,lambdaQueryWrapper);
        return R.success(objectPage);
    }
    //这是登录
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession) {
        User login = userService.login(username, password);
        httpSession.setAttribute("id", login.getId());
        httpSession.setAttribute("username",login.getUsername());
        log.info(getUsernameFromSession(httpSession).toString());
        log.info(getUidFromSession(httpSession).toString());
        return new JsonResult<User>(OK,login);
    }

    @RequestMapping("changePassword")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        // 调用session.getAttribute("")获取uid和username
        Long id = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改密码
        userService.ChangePassword(id, username, oldPassword, newPassword);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    @GetMapping("getById")
    public JsonResult<User> getByUid(HttpSession session) {
        // 从HttpSession对象中获取uid
        Long uid = getUidFromSession(session);
        // 调用业务对象执行获取数据
        User data = userService.getMessageById(uid);
        // 响应成功和数据
        return new JsonResult<User>(OK, data);
    }

    //修改资料
    @RequestMapping("changeInfo")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // 从HttpSession对象中获取uid和username
        Long id = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改用户资料
        userService.changeMessage(id, username, user);
        // 响应成功
        return new JsonResult<Void>(OK);
    }

    //最大上传10mb
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //允许上传的文件类型
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    //上传图片
    @PostMapping("changeAvatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }
        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }
        String parent = "C:\\Users\\a1361\\Desktop\\PetSale\\src\\main\\resources\\front\\page\\a\\";
        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;
        // 创建文件对象，表示保存的头像文件
        File dest = new File(parent+filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }
        // 头像路径
        String avatar =  filename;
        // 从Session中获取uid和username
        Long uid = getUidFromSession(session);
        // 将头像写入到数据库中
        userService.updateAvatarById(uid, avatar);
        // 返回成功头像路径
        return new JsonResult<String>(OK , avatar);
    }

    @GetMapping("queryUser")
    public JsonResult<User> changeAvatar(HttpSession session){
        Long uidFromSession = getUidFromSession(session);
        User byId = userService.getById(uidFromSession);
        if (byId==null){
            return new JsonResult<>(OK,null);
        }
        String avatar = byId.getAvatar();
        return new JsonResult<>(OK,byId);
    }

    @GetMapping("/send")
    public JsonResult<String> sendVerify(@RequestParam("phone") String phone, @RequestParam("username") String username,HttpSession session) {
        // 发送验证码逻辑
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername,username);
        User one = userService.getOne(queryWrapper);
        if (one==null){
            throw new UserNotFoundException("没有该用户，请重试");
        }
        if (one.getPhone() == null || !one.getPhone().equals(phone)) {
            throw new PhoneNotFindException("电话不一致，请重试");
        }
        //这是那个验证码
        String code = ValidateCodeUtils.generateValidateCode4String(4);
        log.info("code={}",code);
        Send.send(phone,code);
        //来个session
        session.setAttribute(phone,code);
        Object attribute = session.getAttribute(phone);
        log.info(attribute.toString());
        return new JsonResult<>(OK,"手机验证码短信发送成功");
    }
    @PostMapping("/findPassword")
    public JsonResult<String> findPassword(@RequestParam("phone") String phone, @RequestParam("username") String username,@RequestParam("code") String code,HttpSession session){
        Object attribute = session.getAttribute(phone);
        String s = attribute.toString();
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User one = userService.getOne(queryWrapper);
        if (one == null){
            throw new UserNotFoundException("用户名未找到");
        }
        if (!s.equals(code)){
            throw new CodeNotMatchException("验证码并不匹配，请重试");
        }
        String password = userService.changePasswordByPhone("123456", one.getSalt());
        one.setPassword(password);
        userService.updateById(one);
        return new JsonResult(OK,"密码已被改为123456，请重新登录");
    }
}
