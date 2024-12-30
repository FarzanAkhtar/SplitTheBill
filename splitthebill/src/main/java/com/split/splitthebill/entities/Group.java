package com.split.splitthebill.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "expense_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq")
    private Long id;
    @Nonnull
    private String name;
    @Column(name = "group_uuid", unique = true, updatable = false)
    private String groupUuid;
    @Column(name = "created_at")
    private String createdAt;
}
