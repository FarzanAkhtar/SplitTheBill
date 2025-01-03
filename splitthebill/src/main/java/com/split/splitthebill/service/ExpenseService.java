package com.split.splitthebill.service;

import com.split.splitthebill.Utils;
import com.split.splitthebill.dtos.*;
import com.split.splitthebill.entities.*;
import com.split.splitthebill.mappers.ExpenseMapper;
import com.split.splitthebill.mappers.GroupMapper;
import com.split.splitthebill.mappers.UserMapper;
import com.split.splitthebill.repositories.*;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ExpenseUserMappingRepository expenseUserMappingRepository;

    @Transactional
    public void createExpense(ExpenseDto expenseDto) {
        String uuid;
        do {
            uuid = Utils.generateRandomUuid();
        } while (expenseRepository.existsByExpenseUuid(uuid));
        Group group = groupRepository.findByGroupUuid(expenseDto.getGroupUuid());
        User addedByUser = userRepository.findByUuid(expenseDto.getAddedBy()).orElseThrow();
        Expense expense = ExpenseMapper.mapTo(expenseDto, uuid, group.getId(), addedByUser.getId());
        Expense savedExpense = expenseRepository.save(expense);
        List<UserShareDto> members = expenseDto.getMembers();
        List<User> users = userRepository.findAllByUuidIn(members.stream().map(UserShareDto::getUserUuid).toList());
        for (UserShareDto member : members) {
            expenseUserMappingRepository.save(
                    ExpenseUserMapping.builder()
                            .id(
                                    ExpenseUserMappingId.builder()
                                            .user(users.stream().filter(u -> u.getUuid().equals(member.getUserUuid())).findFirst().orElseThrow())
                                            .expense(savedExpense)
                                            .group(group)
                                            .build()
                            )
                            .share(member.getShare())
                            .build()
            );
        }
    }
}
