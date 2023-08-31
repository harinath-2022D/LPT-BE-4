package com.example.lptAssignment.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentors")
@Data
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private int rating;
    @OneToMany(mappedBy = "mentor",cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();


}
