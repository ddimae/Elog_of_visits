package com.lavrenko.blog.controllers;

import com.lavrenko.blog.models.*;
import com.lavrenko.blog.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/rating")
    public  String ratingMain(Model model){
        Iterable<Rating> ratings = ratingRepository.findAll();
        model.addAttribute("ratings", ratings);
        return "rat/rating-list";
    }

    @GetMapping("/rating/create")
    public String ratingAdd(Model model){
        Iterable<Rating> ratingIterable = ratingRepository.findAll();
        model.addAttribute("rating", new Rating());
        model.addAttribute("grades", ratingIterable);
        Iterable<Subject> subjectIterable = subjectRepository.findAll();
        model.addAttribute("subjectIterable", subjectIterable);
        Iterable<Student> studentIterable = studentRepository.findAll();
        model.addAttribute("studentIterable", studentIterable);
        return "rat/rating-create";
    }

    @PostMapping("/rating/create")
    public String ratingPostAdd(Rating rating){
        ratingRepository.save(rating);
        return "redirect:/rating";
    }

    @GetMapping("rating/delete/{id_rating}")
    public String ratingDelete(@PathVariable(value = "id_rating") long id_rating, Model model){
        ratingRepository.deleteById(id_rating);
        return "redirect:/rating";
    }

}
