package com.lavrenko.blog.controllers;

import com.lavrenko.blog.models.Groups;
import com.lavrenko.blog.models.Student;
import com.lavrenko.blog.repo.GroupRepository;
import com.lavrenko.blog.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/group")
    public String groupMain(Model model){
        Iterable<Groups> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "group/group-list";
    }

    @GetMapping("/group/create")
    public String groupAdd(Model model){
        return "group/group-create";
    }

    @PostMapping("/group/create")
    public String groupPostAdd(@RequestParam String num_group1, Model model){
        Groups group = new Groups(num_group1);
        groupRepository.save(group);
        return "redirect:/group";
    }

    @GetMapping("/group/{id_group}")
    public String groupDetails(@PathVariable(value = "id_group") long id_group, Model model){
        if (!groupRepository.existsById(id_group)){
            return "redirect:/group";
        }

        Optional<Groups> group = groupRepository.findById(id_group);
        ArrayList<Groups> res = new ArrayList<>();
        group.ifPresent(res :: add);
        model.addAttribute("group", res);

        return "group/group-details";
    }

    @GetMapping("/group/{id_group}/edit")
    public String groupEdit(@PathVariable(value = "id_group") long id_group, Model model){
        if (!groupRepository.existsById(id_group)){
            return "redirect:/group";
        }

        Optional<Groups> group = groupRepository.findById(id_group);
        ArrayList<Groups> res = new ArrayList<>();
        group.ifPresent(res :: add);
        model.addAttribute("group", res);
        return "group/group-edit";
    }

    @PostMapping("/group/{id_group}/edit")
    public String groupPostUpdate(@PathVariable(value = "id_group") long id_group, @RequestParam String num_group1, Model model){
        Groups group = groupRepository.findById(id_group).orElseThrow();
        group.setNum_group1(num_group1);
        groupRepository.save(group);
        return "redirect:/group";
    }

    @PostMapping("/group/{id_group}/remove")
    public String groupPostDelete(@PathVariable(value = "id_group") long id_group, Model model){
        Groups group = groupRepository.findById(id_group).orElseThrow();
        groupRepository.delete(group);
        return "redirect:/group";
    }
}
