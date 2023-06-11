package com.SHOP.TaTa.controller;

import com.SHOP.TaTa.controller.ex.*;
import com.SHOP.TaTa.service.ex.*;
import com.SHOP.TaTa.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;
    public static final int FAIL = 100;

    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户名未找到，请重试");
        }else if (e instanceof PhoneNotFindException) {
            result.setState(4008);
        }else if (e instanceof CodeNotMatchException) {
            result.setState(4009);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("密码错误");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户收货地址超出上限");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("未找到收货地址");
        }else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("非法访问异常");
        }else if (e instanceof ShopNotFoundException) {
            result.setState(4006);
            result.setMessage("商品找不到");
        }else if (e instanceof CarNotFoundException) {
            result.setState(4007);
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("插入数据异常");
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新数据异常");
        } else if (e instanceof DeleteException) {
            result.setState(5002);
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }else if (e instanceof AmountException) {
            result.setState(7001);
        }
        return result;
    }

    /**
     * 从HttpSession对象中获取uid
     * @param session HttpSession对象
     * @return 当前登录的用户的id
     */
    protected final Long getUidFromSession(HttpSession session) {
        return Long.valueOf(session.getAttribute("id").toString());
    }

    /**
     * 从HttpSession对象中获取用户名
     * @param session HttpSession对象
     * @return 当前登录的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
