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
public class ExpenseDto {
    @JsonProperty("expense_uuid")
    private String expenseUuid;
    private String name;
    private List<UserShareDto> members;
    private Double amount;
    @JsonProperty("added_by")
    String addedBy;
    @JsonProperty("group_uuid")
    String groupUuid;
}
