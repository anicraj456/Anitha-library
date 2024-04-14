package org.launchcode.anithalibrary.controller;


import jakarta.validation.Valid;

import org.launchcode.anithalibrary.data.BookinfoRepository;
import org.launchcode.anithalibrary.model.Bookinfo;
import org.launchcode.anithalibrary.model.GoogleBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


//Anitha - what does this controller do?
@Controller
@RequestMapping("booksinfo")
public class BookinfoController {

    @Autowired
    private BookinfoRepository bookinfoRepository;

    @GetMapping
    public String displayBookInfo (@RequestParam(required=false) Integer bookinfoId, Model model) {
        model.addAttribute("title", "Book Information");
        model.addAttribute("bookInfo", "");
        if (bookinfoId == null) {
            model.addAttribute("bookinfos", bookinfoRepository.findAll());
        } else {
            model.addAttribute("bookinfos", bookinfoRepository.findById(bookinfoId));
        }
        return "booksinfo/index";
    }

    @GetMapping("add")
    public String renderCreateBookinfoForm(Model model){
        model.addAttribute("title", "Create Book Request");
        model.addAttribute(new Bookinfo());
        //       model.addAttribute("students", studentRepository.findAll());
        return "booksinfo/add";
    }
    @PostMapping("add")
    public String getBookInfo(@ModelAttribute @Valid Bookinfo newBookInfo, Errors errors, Model model){
        // code API to get the book info from google API using newBookInfo

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Book Request");
            return "booksinfo/add";
        }
        bookinfoRepository.save(newBookInfo);
        return "redirect:/booksinfo";

    }

    @GetMapping ("delete")
    public String displayDeleteBookInfoForm (Model model){
        model.addAttribute("title", "Delete Book");
        model.addAttribute("booksinfo", bookinfoRepository.findAll());
        return "booksinfo/delete";
    }

    @PostMapping ("delete")
    public String processDeleteBookInfoForm(@RequestParam(required = false) int[] BookinfoIds) {
        if (BookinfoIds != null) {
            for (int id : BookinfoIds) {
                bookinfoRepository.deleteById(id);
            }
        }
        return "redirect:/booksinfo";
    }

    @RequestMapping ("search")
    public String renderBookApi (Model model) {
        return "booksinfo/search";
    }

    @PostMapping ("search")
    public String getBooks(@RequestParam(required=false) String searchTerm, Model model) {
//    if (errors.hasErrors()) {
//        model.addAttribute("title", "Search Again");
//        return "booksinfo/search";
//    }
        RestTemplate restTemplate = new RestTemplate();
        if (searchTerm == null) {
            searchTerm = "Java";
        }

        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + searchTerm; // Example API URL
        GoogleBooksResponse response = restTemplate.getForObject(apiUrl, GoogleBooksResponse.class);
        model.addAttribute("books", response.getItems());
        return "booksinfo/search"; // Thymeleaf template name
    }


}