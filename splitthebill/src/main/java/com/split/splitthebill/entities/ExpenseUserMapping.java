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
@Table(name = "expense_user_mapping")
public class ExpenseUserMapping {

    @EmbeddedId
    private ExpenseUserMappingId id;

    @Column(name = "share")
    private Double share;
}
