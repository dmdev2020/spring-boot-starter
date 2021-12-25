package com.dmdev.springboot.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeFilter {

    private String firstName;
    private String lastName;
    private Integer salary;
}
