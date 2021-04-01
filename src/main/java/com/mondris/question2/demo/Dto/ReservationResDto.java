package com.mondris.question2.demo.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationResDto {

    private int id;

    private String firstName;

    private String lastName;

    private Timestamp date;


}
