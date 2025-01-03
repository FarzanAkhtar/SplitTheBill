package com.split.splitthebill.mappers;

import com.split.splitthebill.dtos.ExpenseDto;
import com.split.splitthebill.dtos.GroupBaseDto;
import com.split.splitthebill.dtos.GroupDto;
import com.split.splitthebill.dtos.UserDto;
import com.split.splitthebill.entities.Expense;
import com.split.splitthebill.entities.Group;
import com.split.splitthebill.entities.User;
import com.split.splitthebill.requests.CreateExpenseRequest;
import com.split.splitthebill.requests.CreateGroupRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class ExpenseMapper {
    public static ExpenseDto mapFromReq(CreateExpenseRequest createExpenseRequest) {
        return ExpenseDto.builder()
                .name(createExpenseRequest.getName())
                .amount(createExpenseRequest.getAmount())
                .members(createExpenseRequest.getMembers())
                .groupUuid(createExpenseRequest.getGroupUuid())
                .addedBy(createExpenseRequest.getAddedBy())
                .build();
    }

    public static GroupDto mapFrom(Group group, List<UserDto> members) {
        return GroupDto.builder()
                .name(group.getName())
                .members(members)
                .build();
    }

    public static Expense mapTo(ExpenseDto expenseDto, String uuid, Long groupId, Long userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return Expense.builder()
                .name(expenseDto.getName())
                .expenseUuid(uuid)
                .createdAt(formattedDateTime)
                .groupId(groupId)
                .addedBy(userId)
                .amount(expenseDto.getAmount())
                .build();
    }

    public static GroupBaseDto mapToGroupBaseDto(Group group) {
        return GroupBaseDto.builder()
                .name(group.getName())
                .groupUuid(group.getGroupUuid())
                .build();
    }
}
