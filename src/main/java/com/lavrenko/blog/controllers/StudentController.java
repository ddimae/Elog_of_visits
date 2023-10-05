package com.lavrenko.blog.controllers;

import com.lavrenko.blog.models.Groups;
import com.lavrenko.blog.models.Student;
import com.lavrenko.blog.repo.GroupRepository;
import com.lavrenko.blog.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/stud")
    public String studentMain(Model model){
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "stud/stud-list";
    }

    @GetMapping("/stud/create")
    public String studentAdd(Model model){
        Iterable<Groups> groupsIterable = groupRepository.findAll();
        model.addAttribute("student", new Student());
        model.addAttribute("groupsList", groupsIterable);
        return "stud/stud-create";
    }

    @PostMapping("/stud/create")
    public String studentPostAdd(Student student){
        studentRepository.save(student);
        return "redirect:/stud";
    }

    @GetMapping("/stud/{id_student}")
    public  String studentDetails(@PathVariable(value = "id_student") long id_student, Model model){
        if(!studentRepository.existsById(id_student)){
            return "redirect:/stud";
        }

        Optional<Student> student = studentRepository.findById(id_student);
        ArrayList<Student> res = new ArrayList<>();
        student.ifPresent(res::add);
        model.addAttribute("student", res);
        return "stud/stud-details";
    }

    @GetMapping("/stud/{id_student}/edit")
    public  String studentEdit(@PathVariable(value = "id_student") long id_student, Model model){
        if(!studentRepository.existsById(id_student)){
            return "redirect:/stud";
        }

        Student student = studentRepository.findById(id_student).get();
        model.addAttribute("stud", student);
        Iterable<Groups> groupsIterable = groupRepository.findAll();
        model.addAttribute("groupsIter", groupsIterable);
        return "stud/stud-edit";
    }

    @PostMapping("/stud/edit")
    public String studentEditAdd(Student student){
        studentRepository.save(student);
        return "redirect:/stud";
    }

    @PostMapping("/stud/{id_student}/remove")
    public String studentDelete(@PathVariable(value = "id_student") long id_student, Model model){
        Student student = studentRepository.findById(id_student).orElseThrow();
        studentRepository.delete(student);
        return "redirect:/stud";
    }
}
