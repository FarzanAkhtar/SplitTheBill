package com.split.splitthebill.repositories;

import com.split.splitthebill.entities.Expense;
import com.split.splitthebill.entities.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    boolean existsByExpenseUuid(String expenseUuid);
}

