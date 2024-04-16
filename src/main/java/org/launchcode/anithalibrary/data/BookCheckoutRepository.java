package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Book;
import org.launchcode.anithalibrary.model.BookCheckout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookCheckoutRepository extends CrudRepository<BookCheckout, Integer> {

    List<BookCheckout> findByBook(Book book);
}