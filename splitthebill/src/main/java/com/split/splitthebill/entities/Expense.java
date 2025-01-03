package com.split.splitthebill.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_id_seq")
    private Long id;
    @Column(name = "expense_uuid")
    private String expenseUuid;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "added_by")
    private Long addedBy;
    private Double amount;
    private String name;
    @Column(name = "created_at")
    private String createdAt;
}
