package com.split.splitthebill.controllers;

import com.split.splitthebill.dtos.ExpenseDto;
import com.split.splitthebill.dtos.GroupDto;
import com.split.splitthebill.dtos.SuccessResponse;
import com.split.splitthebill.mappers.ExpenseMapper;
import com.split.splitthebill.mappers.GroupMapper;
import com.split.splitthebill.requests.CreateExpenseRequest;
import com.split.splitthebill.requests.CreateGroupRequest;
import com.split.splitthebill.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/create_expense")
    public ResponseEntity<?> createExpense(@RequestBody CreateExpenseRequest createExpenseRequest) {
        ExpenseDto expenseDto = ExpenseMapper.mapFromReq(createExpenseRequest);
        expenseService.createExpense(expenseDto);
        return new ResponseEntity<>(new SuccessResponse("Expense Created Successfully", HttpStatus.OK.value()), HttpStatus.OK);
    }
}
