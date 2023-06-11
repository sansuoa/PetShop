package com.SHOP.TaTa.service.impl;

import com.SHOP.TaTa.entity.LeaveCondition;
import com.SHOP.TaTa.mapper.LeaveConditionMapper;
import com.SHOP.TaTa.service.LeaveConditionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class LeaveConditionServiceImpl extends ServiceImpl<LeaveConditionMapper, LeaveCondition>implements LeaveConditionService {
}
