package org.launchcode.anithalibrary.controller;

import jakarta.validation.Valid;
import org.launchcode.anithalibrary.data.StudentRepository;
import org.launchcode.anithalibrary.model.Book;
import org.launchcode.anithalibrary.model.Student;
import org.launchcode.anithalibrary.model.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping ("students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    static HashMap<String, String> studentSearchOptions = new HashMap<>();

    public StudentController() {
        studentSearchOptions.put("all", "All");
        studentSearchOptions.put("email", "Email");
        studentSearchOptions.put("lastname", "Student Last Name");
    }

    @GetMapping("/")
    public String displayAllStudents(@RequestParam(required=false) Integer studentId, Model model) {
        model.addAttribute("title", "Student Management");
        Iterable<Student> students;
        if (studentId == null) {
            model.addAttribute("studentSearchOptions", studentSearchOptions);
            students = studentRepository.findAll();
            model.addAttribute("students", students);
            return "students/search";
        } else {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
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

    //Anitha code for Student search 57 to 87
    @GetMapping ("/search")
    public String displaySearchStudentForm (Model model){
        model.addAttribute("studentSearchOptions", studentSearchOptions);
        model.addAttribute("title", "All Students");
        model.addAttribute("students", studentRepository.findAll());
        return "students/search";
    }
    //Anitha code for Student search
    @PostMapping ("/search/results")
    public String processSearchStudent(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Student> students;
        ArrayList<Student> tempStudents = (ArrayList<Student>) studentRepository.findAll();
        students = StudentData.findByColumnAndValue(searchType, searchTerm, studentRepository.findAll());
        model.addAttribute("studentSearchOptions", studentSearchOptions);
        model.addAttribute("searchType",searchType);
        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("students",students);
        model.addAttribute("title", "Students with " + studentSearchOptions.get(searchType) + ": " + searchTerm);
        return "students/search";
    }
    //Anitha code for Student search
    @GetMapping("/detail/{studentId}")
    public String viewStudent(@PathVariable("studentId")String studentId,Model model) {
        Optional<Student> student = studentRepository.findById(Integer.valueOf(studentId));
        if(student.isPresent()){
            Student selectedStudent = student.get();
            model.addAttribute("student", selectedStudent);
        }//else throw error
        return "students/view";
    }


    @GetMapping("add")
    public String renderCreateStudentForm(Model model){
        model.addAttribute("title", "Create Student");
        model.addAttribute(new Student());
        return "students/add";
    }

    @PostMapping("add")
    public String createEvent(@ModelAttribute @Valid Student newStudent, Errors errors, Model model){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Student");
            return "students/add";
        }

        Student studentByEmail = studentRepository.findByContactEmail(newStudent.getContactEmail());
        if(studentByEmail != null){
            return "students/add";
        }
        studentRepository.save(newStudent);
        return "redirect:/students/";

    }

    @GetMapping ("delete")
    public String displayDeleteStudentForm (Model model){
        model.addAttribute("title", "Delete Student");
        model.addAttribute("students", studentRepository.findAll());
        return "students/delete";
    }

    @PostMapping ("delete")
    public String processDeleteStudent(@RequestParam(required = false) int[] StudentIds){
        if (StudentIds != null)
        {
            for (int id : StudentIds) {
                studentRepository.deleteById(id);
            }
        }
        return "redirect:/students/";

    }


    @GetMapping ("update")
    public String displayDeleteStudentForm (@RequestParam(required = false) Integer studentId, Model model){
        model.addAttribute("title", "Update Student");
        model.addAttribute("student", studentRepository.findById(studentId));
        return "students/update";
    }

    @PostMapping ("update")
    public String updateStudents(@ModelAttribute @Valid Student student, Model model) {
//    public String updateStudent (@RequestParam(required = false) Integer studentId, Model model) {
        /* Iterable<Student> students;
        if (studentId == null) {
            students = studentRepository.findAll();
            model.addAttribute("students", students);
            return "students/index";
        } else {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            Student student = optionalStudent.get();
            student.getId();
            student.setFirstname(studentfirstname);
            student.setLastname(studentlastname);
            student.setContactEmail(studentcontactemail);
            studentRepository.save(student);
            studentId = null;
            //                  Student newStudent1 = new Student();
            //       model.addAttribute("title", "Student Management");
            //       if (studentId == null) {
            //           model.addAttribute("students", studentRepository.findAll());
            //       } else {
            //           newStudent1.setFirstname() = newStudent.getFirstname();
//
            //          studentRepository.findById(studentId).gfirstname)
            //          model.addAttribute("students", studentRepository.findById(studentId));
            //      }
            return "redirect:/students/";
        }*/
        studentRepository.save(student);
        return "redirect:/students/";
    }

    //    @RequestMapping("search")
//    public String search(Model model) {
//        model.addAttribute("columns", columnChoices);
////        model.addAttribute("title", "Search Student");
////        model.addAttribute("Students", studentRepository.findAll());
//        return "search";
//    }
    @PostMapping ("add/view")
    public String processViewStudent(@RequestParam(required = false) int[] studentId, Model model){
        if (studentId != null)
        {
            for (int id : studentId) {
                model.addAttribute("Student ID", studentRepository.findById(id));
            }
        }
        return "students/search";

    }
}