package com.ZHL.TaTa.dto;

import com.ZHL.TaTa.entity.LeaveCondition;
import com.ZHL.TaTa.entity.PetLeave;
import lombok.Data;
import java.util.List;

@Data
public class PetLeaveDto extends PetLeave {

    private List<LeaveCondition> leaveConditions;

    private String categoryName;
}
