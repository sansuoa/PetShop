package com.SHOP.TaTa.dto;

import com.SHOP.TaTa.entity.LeaveCondition;
import com.SHOP.TaTa.entity.PetLeave;
import lombok.Data;
import java.util.List;

@Data
public class PetLeaveDto extends PetLeave {

    private List<LeaveCondition> leaveConditions;

    private String categoryName;
}
