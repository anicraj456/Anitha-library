package org.launchcode.anithalibrary.controller;

import org.launchcode.anithalibrary.data.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
@Controller
@RequestMapping(value = "list")

public class ListController {


    @Autowired
    private StudentRepository studentRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("id", "ID");
        //      columnChoices.put("studentfn", "Student First Name");
        //      columnChoices.put("studentln", "Student Last Name");

    }

    @RequestMapping("")
    public String list(Model model) {
        // Pass employer and skill data to the view
        model.addAttribute("students", studentRepository.findAll());

        return "list";
    }

}