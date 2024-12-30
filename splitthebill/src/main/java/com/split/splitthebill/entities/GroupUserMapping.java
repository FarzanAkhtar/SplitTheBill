package com.split.splitthebill.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "group_user_mapping")
public class GroupUserMapping {
    @EmbeddedId
    private GroupUserMappingId id;
}

