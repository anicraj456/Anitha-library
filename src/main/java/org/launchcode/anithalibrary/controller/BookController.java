package org.launchcode.anithalibrary.controller;

import jakarta.validation.Valid;
import org.launchcode.anithalibrary.data.BookCheckoutRepository;
import org.launchcode.anithalibrary.data.BookRepository;
import org.launchcode.anithalibrary.model.Book;
import org.launchcode.anithalibrary.model.BookCheckout;
import org.launchcode.anithalibrary.model.BooksInventory;
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

    @Autowired
    private BookCheckoutRepository bookCheckoutRepository;

    @GetMapping("/") //http://localhost:8080/books/
    public String index(Model model) {
        model.addAttribute("title", "All Books");
        model.addAttribute("books", bookRepository.findAll());
        return "books/index";
    }

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
        newBook.setAvailableCopiesToIssue(newBook.getCopies());

        bookRepository.save(newBook);


        return "redirect:";
    }

    @GetMapping("update") //http://localhost:8080/books/update?bookId=xx
    public String displayUpdateBookForm(@RequestParam Integer bookId, Model model) {
        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();
        model.addAttribute(book);
        model.addAttribute(new BooksInventory());

        return "books/update";
    }

    @PostMapping("update") //http://localhost:8080/books/update
    public String processUpdateBookForm(@ModelAttribute @Valid Book book, @ModelAttribute @Valid BooksInventory booksInventory,
                                        Errors errors,
                                        Model model) {


        if (!errors.hasErrors()) {

            if (booksInventory.getBooksToAdd() > 0) {
                int copies = book.getCopies() + booksInventory.getBooksToAdd();
                int availableCopies = book.getAvailableCopiesToIssue() + booksInventory.getBooksToAdd();

                book.setCopies(copies);
                book.setAvailableCopiesToIssue(availableCopies);
            }
            if (booksInventory.getBooksToRemove() > 0) {
                int copies = book.getCopies() - booksInventory.getBooksToRemove();

                int availableCopies = book.getAvailableCopiesToIssue() - booksInventory.getBooksToRemove();
                book.setCopies(copies);
                book.setAvailableCopiesToIssue(availableCopies);
            }
            bookRepository.save(book);

            return "redirect:detail?bookId=" + book.getId();
        }


        return "redirect:update";
    }

    @GetMapping("detail") //http://localhost:8080/books/detail?bookId=xx
    public String displayBookDetail(@RequestParam Integer bookId, Model model) {
        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();
        model.addAttribute(book);

        return "books/detail";
    }

    @GetMapping("deletelist") //http://localhost:8080/books/deletelist
    public String displayDeleteBooksForm(Model model) {
        model.addAttribute("title", "Delete Books");
        model.addAttribute("books", bookRepository.findAll());
        return "books/delete";
    }


    @PostMapping("deletebooks") //http://localhost:8080/books/delete
    public String processDeleteBooks(@RequestParam(required = false) Integer[] bookIds) {

        if (bookIds != null) {
            for (int id : bookIds) {
                bookRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("delete") //http://localhost:8080/books/delete?bookId=xx
    public String displayBookDelete(@RequestParam Integer bookId, Model model) {
        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();
        model.addAttribute(book);

        return "books/delete";
    }

    @PostMapping("delete") //http://localhost:8080/books/delete?bookId=xxxx
    public String processDeleteBook(@RequestParam(required = true) Integer bookId) {

        if (bookId != null) {

            bookRepository.deleteById(bookId);
        }


        return "redirect:";
    }

    @GetMapping("checkout") //http://localhost:8080/books/checkout
    public String displayCheckout(Model model) {
        model.addAttribute(new BookCheckout());

        return "books/checkout";
    }

    @PostMapping("checkout") //http://localhost:8080/books/checkout?bookId=xxxx
    public String processCheckout(@ModelAttribute @Valid BookCheckout newBookCheckout, int bookId,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "books/checkout";
        }


        int studentId = newBookCheckout.getStudentId();

        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();
        newBookCheckout.setBook(book);

        int availableCopies = book.getAvailableCopiesToIssue();

        if (newBookCheckout.isCheckout()) {
            availableCopies--;
        } else {

            availableCopies++;
        }
        bookCheckoutRepository.save(newBookCheckout);

        book.setAvailableCopiesToIssue(availableCopies);

        bookRepository.save(book);

        return "redirect:";
    }
}