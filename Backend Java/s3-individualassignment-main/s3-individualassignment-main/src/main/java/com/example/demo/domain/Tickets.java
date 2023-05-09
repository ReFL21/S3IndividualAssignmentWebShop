package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {
    private long id;
    private String date;
    private String place;
    private FootballMatch match;
    private long price;
    private long quantity;

}
