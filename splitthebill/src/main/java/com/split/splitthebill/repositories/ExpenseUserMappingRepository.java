package com.split.splitthebill.repositories;

import com.split.splitthebill.entities.Expense;
import com.split.splitthebill.entities.ExpenseUserMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseUserMappingRepository extends CrudRepository<ExpenseUserMapping, Long> {
}

