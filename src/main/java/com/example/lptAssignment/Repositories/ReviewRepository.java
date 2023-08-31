package com.example.lptAssignment.Repositories;

import com.example.lptAssignment.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Query("SELECT r.feedback FROM Review r WHERE r.mentorid = ?1")
    List<String> findFeedbackByMentorId(int mentorId);
}
