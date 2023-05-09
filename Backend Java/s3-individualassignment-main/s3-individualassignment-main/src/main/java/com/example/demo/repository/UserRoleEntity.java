package com.example.demo.repository;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
@Entity
@Data
@Builder
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserEntity user;
}
