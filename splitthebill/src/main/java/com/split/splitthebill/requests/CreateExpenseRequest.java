package com.split.splitthebill.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.split.splitthebill.dtos.UserDto;
import com.split.splitthebill.dtos.UserShareDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateExpenseRequest {
    String name;
    List<UserShareDto> members;
    Double amount;
    @JsonProperty("added_by")
    String addedBy;
    @JsonProperty("group_uuid")
    String groupUuid;
}
