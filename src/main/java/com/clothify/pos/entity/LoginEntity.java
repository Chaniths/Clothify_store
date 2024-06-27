package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Login")
@Table(name = "Login")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer num;
    @Column(unique = true)
    private String loginId;
    private String adminType;
    private String email;
    private String saltValue;
    private String password;
}
