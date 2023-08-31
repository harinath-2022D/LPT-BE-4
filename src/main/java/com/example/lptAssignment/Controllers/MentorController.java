package com.example.lptAssignment.Controllers;

import com.example.lptAssignment.Services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentor")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping("/addMentor")
    public ResponseEntity<String> addMentor(@RequestParam("name") String name, @RequestParam("email") String email){
        String result = mentorService.addMentor(name,email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/recommendStudent")
    public ResponseEntity<String> recommendStudent(@RequestParam("mentorid") int mentorid, @RequestParam("userid") int userid, @RequestParam("opinion") String opinion){
        String result = mentorService.recommendStudent(mentorid,userid,opinion);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
