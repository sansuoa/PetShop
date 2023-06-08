package com.ZHL.TaTa.service.impl;

import com.ZHL.TaTa.entity.LeaveCondition;
import com.ZHL.TaTa.mapper.LeaveConditionMapper;
import com.ZHL.TaTa.service.LeaveConditionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class LeaveConditionServiceImpl extends ServiceImpl<LeaveConditionMapper, LeaveCondition>implements LeaveConditionService {
}
