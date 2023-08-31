package com.example.lptAssignment.Controllers;

import com.example.lptAssignment.Dtos.LetterOfRecommendation;
import com.example.lptAssignment.Dtos.MentorDetailsDto;
import com.example.lptAssignment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam("name") String name,@RequestParam("email") String email){
        String result =  userService.addUser(name,email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("/giveRating")
    public ResponseEntity<String> giveRating(@RequestParam("userid") int userid,@RequestParam("mentorid") int mentorid,@RequestParam("rating") int rating){
        String result = userService.giveRating(userid,mentorid,rating);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/giveReview")
    public ResponseEntity<String> giveReview(@RequestParam("userid") int userid, @RequestParam("mentorid") int mentorid, @RequestParam("Feedback") String feedback){
        String result = userService.giveReview(userid,mentorid,feedback);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getMentorDetails")
    public List<MentorDetailsDto> getMentorDetails(@RequestParam("rating") int rating){
        return userService.getMentorDetails(rating);
    }

    @GetMapping("/getLetterOfRecommendation")
    public LetterOfRecommendation getLetterOfRecommendation(@RequestParam("link") String link){
        return userService.getLetterOfRecommendation(link);
    }
}
