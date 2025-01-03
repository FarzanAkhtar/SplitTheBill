package com.split.splitthebill.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    @JsonProperty("group_uuid")
    private String groupUuid;
    private String name;
    private List<UserDto> members;
    private List<ExpenseDto> expenses;
}
