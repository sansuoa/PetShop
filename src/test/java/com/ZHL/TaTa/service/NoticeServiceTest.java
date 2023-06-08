package com.ZHL.TaTa.service;

import com.ZHL.TaTa.entity.Notice;
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
public class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;

    @Test
    public void create() {
        List<Notice> list = noticeService.list();
        log.info(list.toString());
    }
}
