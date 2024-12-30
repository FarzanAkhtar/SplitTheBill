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
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "added_by")
    private Long addedBy;
    private Double amount;
    private String name;
    private String category;
    @Column(name = "created_at")
    private Long createdAt;
}
