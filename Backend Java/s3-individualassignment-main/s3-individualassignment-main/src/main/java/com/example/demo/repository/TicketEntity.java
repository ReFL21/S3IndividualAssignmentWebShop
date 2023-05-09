package com.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table( name = "ticket")
@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(max = 45)
    @Column(name = "date")
    private String date;

    @NotBlank
    @Length(max = 45)
    @Column(name = "place")
    private String place;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "match_id")
    private FootballMatchEntity match;

    @Column(name = "price")
    private long price;

    @Column(name = "quantity")
    private long quantity;

}
