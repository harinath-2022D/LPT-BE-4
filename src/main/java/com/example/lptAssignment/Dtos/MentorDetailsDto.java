package com.example.lptAssignment.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class MentorDetailsDto {
    private String name;
    private String email;
    private List<String> feedbacks;
}
