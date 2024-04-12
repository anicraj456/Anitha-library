package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.BookCheckout;
import org.springframework.data.repository.CrudRepository;

public interface BookCheckoutRepository extends CrudRepository<BookCheckout, Integer> {
}