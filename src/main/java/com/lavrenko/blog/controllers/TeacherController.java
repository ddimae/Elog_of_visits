package com.lavrenko.blog.controllers;

import com.lavrenko.blog.models.Groups;
import com.lavrenko.blog.models.Subject;
import com.lavrenko.blog.models.Teacher;
import com.lavrenko.blog.repo.GroupRepository;
import com.lavrenko.blog.repo.SubjectRepository;
import com.lavrenko.blog.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/teacher")
    public  String teacherMain(Model model){
        Iterable<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);
        return "teach/teacher-list";
    }

    @GetMapping("/teacher/create")
    public  String teacherAdd(Model model){
        return "teach/teacher-create";
    }

    @PostMapping("/teacher/create")
    public String teacherPostAdd(@RequestParam String name_teacher, Model model){
        Teacher teacher = new Teacher(name_teacher);
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/teacher/{id_teacher}")
    public  String teacherDetails(@PathVariable(value = "id_teacher") long id_teacher, Model model){
        if(!teacherRepository.existsById(id_teacher)){
            return "redirect:/teacher";
        }

        Optional<Teacher> teacher = teacherRepository.findById(id_teacher);
        ArrayList<Teacher> res = new ArrayList<>();
        teacher.ifPresent(res::add);
        model.addAttribute("teacher", res);
        return "teach/teacher-details";
    }

    @GetMapping("/teacher/{id_teacher}/edit")
    public  String teacherEdit(@PathVariable(value = "id_teacher") long id_teacher,
                               Model model){
        if(!teacherRepository.existsById(id_teacher)){
            return "redirect:/teacher";
        }

        Optional<Teacher> teacher = teacherRepository.findById(id_teacher);
        ArrayList<Teacher> res = new ArrayList<>();
        teacher.ifPresent(res::add);
        model.addAttribute("teacher", res);

        return "teach/teacher-edit";
    }

    @PostMapping("/teacher/{id_teacher}/edit")
    public String teacherPostUpdate(@PathVariable(value = "id_teacher") long id_teacher,
                                    @RequestParam String name_teacher,
                                 Model model){
        Teacher teacher = teacherRepository.findById(id_teacher).orElseThrow();
        teacher.setName_teacher(name_teacher);
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }

    @PostMapping("/teacher/{id_teacher}/remove")
    public String teacherPostDelete(@PathVariable(value = "id_teacher") long id_teacher, Model model){
        Teacher teacher = teacherRepository.findById(id_teacher).orElseThrow();
        teacherRepository.delete(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/teacher/{id_teacher}/sub")
    public String subShow(@PathVariable(value = "id_teacher") long id_teacher, Model model){
        Teacher teacher = teacherRepository.findById(id_teacher).orElseThrow();
        model.addAttribute("tea", teacher);
        Iterable<Subject> subj = subjectRepository.findAll();
        model.addAttribute("subj", subj);
        Iterable<Groups> groupsIterable = groupRepository.findAll();
        model.addAttribute("groupsIterable", groupsIterable);
        return "/teach/teacher-sub";
    }

    @PostMapping("/teacher/sub")
    public String subAdd(Teacher teacher){
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }

}
