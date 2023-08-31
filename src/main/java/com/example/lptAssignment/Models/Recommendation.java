package com.example.lptAssignment.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String mentorname;
    private String opinion;
    private String link;
    private String recommendationid;
}
