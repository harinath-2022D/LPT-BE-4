package com.example.lptAssignment.Services;

import com.example.lptAssignment.Models.Mentor;
import com.example.lptAssignment.Models.Recommendation;
import com.example.lptAssignment.Models.User;
import com.example.lptAssignment.Repositories.MentorRepository;
import com.example.lptAssignment.Repositories.RecommendationRepository;
import com.example.lptAssignment.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    public String addMentor(String name, String email) {
        Mentor mentor = new Mentor();
        mentor.setName(name);
        mentor.setEmail(email);
        mentor.setRating(0);
        mentorRepository.save(mentor);
        return "mentor added successfully !!!";
    }
    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public String recommendStudent(int mentorid, int userid, String opinion) {
        String address = generateUUID();

        Recommendation recommendation = new Recommendation();
        User user = userRepository.findById(userid).get();
        Mentor mentor = mentorRepository.findById(mentorid).get();
        recommendation.setMentorname(mentor.getName());
        recommendation.setUsername(user.getName());
        recommendation.setOpinion(opinion);
        recommendation.setRecommendationid(address);
        String link = String.format("https://webapp.com/%s",address);
        recommendation.setLink(link);


        recommendationRepository.save(recommendation);
        return link;
    }
}
