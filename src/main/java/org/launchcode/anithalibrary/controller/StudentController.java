package org.launchcode.anithalibrary.controller;

import jakarta.validation.Valid;
import org.launchcode.anithalibrary.data.StudentRepository;
import org.launchcode.anithalibrary.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("add")
    public String renderCreateStudentForm(Model model){
        model.addAttribute("title", "Create Student");
        model.addAttribute(new Student());
        model.addAttribute("students", studentRepository.findAll());
        return "student/add";
    }

    @PostMapping("add")
    public String createEvent(@ModelAttribute @Valid Student newStudent, Errors errors, Model model){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Student");
            return "students/add";
        }
        studentRepository.save(newStudent);
        return "redirect:/students";

    }

    /*@GetMapping ("delete")
    public String displayDeleteStudentForm (Model model){
        model.addAttribute("title", "Delete Student");
        model.addAttribute("events", studentRepository.findAll());
        return "student/delete";
    }

    @PostMapping ("delete")
    public String processDeleteEvent(@RequestParam(required = false) int[] studentIds){
        if (studentIds != null)
        {
            for (int id : studentIds) {
                studentRepository.deleteById(id);
            }
        }
        return "redirect:/students";

    }*/

    /*@GetMapping ("search")
    public String displaySearchStudentForm (Model model){
        model.addAttribute("title", "Search Student");
        model.addAttribute("Students", studentRepository.findAll());
        return "student/search";
    }

    @PostMapping ("search")
    public String processSearchStudent(@RequestParam(required = false) int[] studentId, Model model){
        if (studentId != null)
        {
            for (int id : studentId) {
                model.addAttribute("Student ID", studentRepository.findById(id));
            }
        }
        return "redirect:/students";

    }*/
}
