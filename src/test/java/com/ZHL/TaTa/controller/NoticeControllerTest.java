package com.ZHL.TaTa.controller;

import com.ZHL.TaTa.entity.Notice;
import com.ZHL.TaTa.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: 张宏利
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class NoticeControllerTest {
    @Autowired
    private NoticeController noticeController;

    @Test
    public void test(){
        JsonResult<List<Notice>> notice = noticeController.getNotice();
        log.info(notice.toString());
    }
}
