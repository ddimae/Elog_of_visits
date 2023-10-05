package com.lavrenko.blog.controllers;

import com.lavrenko.blog.models.Subject;
import com.lavrenko.blog.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/subject")
    public String subjectMain(Model model){
        Iterable<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects", subjects);
        return "sub/subject-list";
    }

    @GetMapping("/subject/create")
    public String subjectsAdd(Model model){
        return "sub/subject-create";
    }

    @PostMapping("/subject/create")
    public String subjectPostAdd(@RequestParam String name_sub, Model model){
        Subject subject = new Subject(name_sub);
        subjectRepository.save(subject);
        return "redirect:/subject";
    }

    @GetMapping("/subject/{id_sub}")
    public String subjectDetails(@PathVariable(value = "id_sub") long id_sub, Model model){
        if (!subjectRepository.existsById(id_sub)){
            return "redirect:/subject";
        }

        Optional<Subject> subject = subjectRepository.findById(id_sub);
        ArrayList<Subject> res = new ArrayList<>();
        subject.ifPresent(res :: add);
        model.addAttribute("subject", res);
        return "sub/subject-details";
    }

    @GetMapping("/subject/{id_sub}/edit")
    public String subjectEdit(@PathVariable(value = "id_sub") long id_sub, Model model){
        if (!subjectRepository.existsById(id_sub)){
            return "redirect:/subject";
        }

        Optional<Subject> subject = subjectRepository.findById(id_sub);
        ArrayList<Subject> res = new ArrayList<>();
        subject.ifPresent(res :: add);
        model.addAttribute("subject", res);
        return "sub/subject-edit";
    }

    @PostMapping("/subject/{id_sub}/edit")
    public String subjectPostUpdate(@PathVariable(value = "id_sub") long id_sub, @RequestParam String name_sub, Model model){
        Subject subject = subjectRepository.findById(id_sub).orElseThrow();
        subject.setName_sub(name_sub);
        subjectRepository.save(subject);
        return "redirect:/subject";
    }

    @PostMapping("/subject/{id_sub}/remove")
    public String subjectPostDelete(@PathVariable(value = "id_sub") long id_sub, Model model){
        Subject subject = subjectRepository.findById(id_sub).orElseThrow();
        subjectRepository.delete(subject);
        return "redirect:/subject";
    }

}
