package com.example.lptAssignment.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userid;

    private int mentorid;

    private String feedback;


}
