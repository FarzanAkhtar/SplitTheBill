package com.split.splitthebill.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Long id;
    private String name;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "encrypted_password")
    private String encryptedPassword;
    private String salt;
    @Column(unique = true, updatable = false)
    private String uuid;
}
