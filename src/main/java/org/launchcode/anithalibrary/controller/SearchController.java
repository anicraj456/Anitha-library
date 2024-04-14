package org.launchcode.anithalibrary.controller;


import org.launchcode.anithalibrary.data.StudentRepository;
import org.launchcode.anithalibrary.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.launchcode.anithalibrary.controller.ListController.columnChoices;

import java.util.Optional;

//Anitha - duplicate should be deleted? all the functions are already in studentcontroller

@Controller
@RequestMapping("students/search")
public class SearchController {
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
//        model.addAttribute("title", "Search Student");
//        model.addAttribute("Students", studentRepository.findAll());
        return "students/search";
    }

//

    @PostMapping("update")
    public String displayStudents(@RequestParam(required = false) Integer searchTerm, Model model) {
        model.addAttribute("title", "Student Management");
        Iterable<Student> students;
        System.out.println(searchTerm);
        if (searchTerm == null) {
            students = studentRepository.findAll();
            model.addAttribute("students", students);
            return "students/index";
        } else {
            Optional<Student> optionalStudent = studentRepository.findById(searchTerm);
            Student student = optionalStudent.get();
            //        model.addAttribute("students1", student);
            model.addAttribute("studentId", student.getId());
            model.addAttribute("studentfirstname", student.getFirstname());
            model.addAttribute("studentlastname", student.getLastname());
            model.addAttribute("studentcontactemail", student.getContactEmail());
            //        return "students/index";
            return "students/update";
        }
    }

//    @PostMapping("results")
//    public String processSearchStudent(Model model, @RequestParam String searchType, @RequestParam Integer searchTerm) {
//        Optional<Student> students;
//        if (searchTerm != null) {
//            model.addAttribute("title", "Search Student");
//            students = studentRepository.findById(searchTerm);
//            model.addAttribute("Students", students);
//        }
//        return "students/update";
//    }


}