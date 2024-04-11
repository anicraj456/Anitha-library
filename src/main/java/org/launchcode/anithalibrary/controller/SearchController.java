package org.launchcode.anithalibrary.controller;

import org.launchcode.anithalibrary.data.BookRepository;
import org.launchcode.anithalibrary.data.StudentBookRepository;
import org.launchcode.anithalibrary.data.StudentRepository;
import org.launchcode.anithalibrary.model.Book;
import org.launchcode.anithalibrary.model.BookData;
import org.launchcode.anithalibrary.model.Student;
import org.launchcode.anithalibrary.model.StudentBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentBookRepository studentBookRepository;

    @Autowired
    private StudentRepository studentRepository;

    static HashMap<String, String> bookSearchOptions = new HashMap<>();
    static HashMap<String, String> studentSearchOptions = new HashMap<>();

    public SearchController () {

        bookSearchOptions.put("all", "All");
        bookSearchOptions.put("name", "Book Name");
        bookSearchOptions.put("authorName", "Author Name");
        bookSearchOptions.put("genre", "Genre");
        studentSearchOptions.put("email", "Email");
        studentSearchOptions.put("lastname", "Student Last Name");

    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("columns", bookSearchOptions);
        return "searchbook/search";
    }

    @PostMapping("/search/results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Book> books;
        ArrayList<Book> tempBooks = (ArrayList<Book>) bookRepository.findAll();
        books = BookData.findByColumnAndValue(searchType, searchTerm, bookRepository.findAll());
        model.addAttribute("columns", bookSearchOptions);
        model.addAttribute("title", "Books with " + bookSearchOptions.get(searchType) + ": " + searchTerm);
        model.addAttribute("books", books);
        return "searchbook/search";
    }

    @GetMapping("/books/view/{bookId}")
    public String viewBook(@PathVariable("bookId")String bookId,Model model) {

        Optional<Book> book = bookRepository.findById(Integer.valueOf(bookId));
        if(book.isPresent()){
            Book selectedBook = book.get();
            model.addAttribute("book", selectedBook);
            // to check which student took that book 71 -79
           /* List<Student> studentsThatHasThisBook = new ArrayList<>();
            List<StudentBook> studentBookList = (List<StudentBook>)studentBookRepository.findAll();
            for(int i=0;i<studentBookList.size();i++){
               StudentBook studentBook = studentBookList.get(i);
                if(studentBook.getId().getBookId() == selectedBook.getId()){
                   studentsThatHasThisBook.add(studentBook.getStudent());
                }
            }
            model.addAttribute("studentsThatHasThisBook", studentsThatHasThisBook);*/
        }//else throw error
        return "searchbook/view";
    }

    @GetMapping ("/search/student")
    public String displaySearchStudentForm (Model model){
        model.addAttribute("studentSearchOptions", studentSearchOptions);
        return "students/search";
    }

    @PostMapping ("/search/student/results")
    public String processSearchStudent(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        model.addAttribute("studentSearchOptions", studentSearchOptions);
        if(searchType.equals("Email")){
            Student student = studentRepository.findByContactEmail(searchTerm);
            if(searchTerm.equalsIgnoreCase(student.getContactEmail())){
                model.addAttribute("students",student);
            }
        }else{
            Student student = studentRepository.findByLastname(searchTerm);
            model.addAttribute("students",student);
        }
    return "students/search";
    }

    @GetMapping("/student/view/{studentId}")
    public String viewStudent(@PathVariable("studentId")String studentId,Model model) {

        Optional<Student> student = studentRepository.findById(Integer.valueOf(studentId));
        if(student.isPresent()){
            Student selectedStudent = student.get();
            model.addAttribute("student", selectedStudent);
        }//else throw error
        return "students/view";
    }
}


