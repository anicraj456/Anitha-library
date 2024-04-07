package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
