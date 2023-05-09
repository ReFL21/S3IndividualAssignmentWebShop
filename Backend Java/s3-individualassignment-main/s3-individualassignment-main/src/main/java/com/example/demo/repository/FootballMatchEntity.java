package com.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Table(name = "matches")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballMatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(max = 50)
    @Column(name = "first_team")
    private String firstTeam;

    @NotBlank
    @Length(max = 50)
    @Column(name = "second_team")
    private String secondTeam;

    @NotBlank
    @Length(max = 50)
    @Column(name = "date")
    private String date;
}
