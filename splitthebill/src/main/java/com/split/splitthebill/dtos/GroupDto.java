package com.split.splitthebill.dtos;

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
    private String name;
    private List<UserDto> members;
    private List<ExpenseDto> expenses;
}
