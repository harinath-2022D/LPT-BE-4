package com.example.lptAssignment.Repositories;

import com.example.lptAssignment.Models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation,Integer> {

    Optional<Recommendation> findByLink(String link);

}
