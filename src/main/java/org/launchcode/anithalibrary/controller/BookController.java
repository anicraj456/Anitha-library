package org.launchcode.anithalibrary.controller;

import jakarta.validation.Valid;
import org.launchcode.anithalibrary.data.BookRepository;
import org.launchcode.anithalibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("add") //http://localhost:8080/books/add
    public String displayAddBookForm(Model model) {

        model.addAttribute(new Book());

        return "books/add";
    }

    @PostMapping("add")  //http://localhost:8080/books/add
    public String processAddBookForm(@ModelAttribute @Valid Book newBook,
                                     Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "books/add";
        }
        bookRepository.save(newBook);
        return "redirect:";
    }

    /*@GetMapping("update") //http://localhost:8080/books/update?bookId=xx
    public String displayUpdateBookForm(@RequestParam Integer bookId, Model model){
        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();
        model.addAttribute(book);
        model.addAttribute(new BooksInventory());

        return "books/update";
    }
    @PostMapping("update") //http://localhost:8080/books/update
    public String processUpdateBookForm(@ModelAttribute @Valid Book book,@ModelAttribute @Valid BooksInventory booksInventory,
                                        Errors errors,
                                        Model model) {

        if (!errors.hasErrors()) {

            if(booksInventory.getBooksToAdd()>0) {
                int copies=book.getCopies()+ booksInventory.getBooksToAdd();
                book.setCopies(copies);
            }
            if(booksInventory.getBooksToRemove()>0){
                int copies= book.getCopies()- booksInventory.getBooksToRemove();
                book.setCopies(copies);
            }
            bookRepository.save(book);

            return "redirect:detail?bookId=" + book.getId();
        }
        return "redirect:update";
    }*/

    @GetMapping("detail") //http://localhost:8080/books/detail?bookId=xx
    public String displayBookDetail(@RequestParam Integer bookId, Model model){
        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();
        model.addAttribute(book);

        return "books/detail";
    }

    @GetMapping("delete")
    public String displayDeleteBookForm(Model model) {
        model.addAttribute("title", "Delete Books");
        model.addAttribute("books", bookRepository.findAll());
        return "books/delete";
    }

    @PostMapping("delete")
    public String processDeleteBook(@RequestParam(required = false) Integer[] bookIds) {

        if (bookIds != null) {
            for (int id : bookIds) {
                bookRepository.deleteById(id);
            }
        }

        return "redirect:/books";
    }
}
