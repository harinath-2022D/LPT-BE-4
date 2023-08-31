package com.example.lptAssignment.Services;

import com.example.lptAssignment.Dtos.LetterOfRecommendation;
import com.example.lptAssignment.Dtos.MentorDetailsDto;
import com.example.lptAssignment.Exceptions.InvalidMentor;
import com.example.lptAssignment.Exceptions.InvalidUser;
import com.example.lptAssignment.Models.Mentor;
import com.example.lptAssignment.Models.Recommendation;
import com.example.lptAssignment.Models.Review;
import com.example.lptAssignment.Models.User;
import com.example.lptAssignment.Repositories.MentorRepository;
import com.example.lptAssignment.Repositories.RecommendationRepository;
import com.example.lptAssignment.Repositories.ReviewRepository;
import com.example.lptAssignment.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    public User getUserWithId(int userid) throws InvalidUser{
        Optional<User> optionalUser =  userRepository.findById(userid);
        if(optionalUser.isEmpty()){
            throw new InvalidUser("user not found");
        }else
            return optionalUser.get();
    }

    public Mentor getMentorWithId(int mentorid) throws InvalidMentor{
        Optional<Mentor> optionalMentor =  mentorRepository.findById(mentorid);
        if(optionalMentor.isEmpty()){
            throw new InvalidMentor("mentor not found");
        }else
            return optionalMentor.get();
    }

    public String giveRating(int userid, int mentorid, int rating) {
        User user;
        try {
             user = getUserWithId(userid);
        }catch (Exception e){
            return e.getMessage();
        }

        Mentor mentor;
        try {
            mentor = getMentorWithId(mentorid);
        }catch (Exception e){
            return e.getMessage();
        }

        int mentorRating =(mentor.getRating() * mentor.getUsers().size()) + rating;
        mentor.getUsers().add(user);
        mentor.setRating(mentorRating / mentor.getUsers().size());
        user.setMentor(mentor);
        mentorRepository.save(mentor);
        return "success";
    }

    public String addUser(String name, String email) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        userRepository.save(user);
        return "user added successfully !!!";

    }

    public String giveReview(int userid, int mentorid, String feedback) {
        Review review = new Review();
        review.setFeedback(feedback);
        review.setMentorid(mentorid);
        review.setUserid(userid);
        reviewRepository.save(review);
        return "review given !!!";
    }

    public List<MentorDetailsDto> getMentorDetails(int rating) {
        List<Mentor> mentorList = mentorRepository.findAll();
        for (Mentor mentor : mentorList){
            if(mentor.getRating() < rating){
                mentorList.remove(mentor);
            }
        }
        List<MentorDetailsDto> mentorDetailsDtoList = new ArrayList<>();
        for(int i=0; i<mentorList.size(); i++){
            int mentorid = mentorList.get(i).getId();
            List<String> feedbacks = reviewRepository.findFeedbackByMentorId(mentorid);
            MentorDetailsDto mentorDetailsDto = new MentorDetailsDto();
            mentorDetailsDto.setName(mentorList.get(i).getName());
            mentorDetailsDto.setEmail(mentorList.get(i).getEmail());
            mentorDetailsDto.setFeedbacks(feedbacks);
            mentorDetailsDtoList.add(mentorDetailsDto);
        }
        return mentorDetailsDtoList;
    }

    public LetterOfRecommendation getLetterOfRecommendation(String link) {
        Optional<Recommendation> recommendation = recommendationRepository.findByLink(link);
        Recommendation recommendation1 = recommendation.get();
        LetterOfRecommendation letterOfRecommendation = new LetterOfRecommendation();

        if(recommendation.isPresent()){
            letterOfRecommendation.setUser(recommendation1.getUsername());
            letterOfRecommendation.setMentor(recommendation1.getMentorname());
            letterOfRecommendation.setText(recommendation1.getOpinion());
            letterOfRecommendation.setId(recommendation1.getRecommendationid());
        }
        return letterOfRecommendation;
    }
}
