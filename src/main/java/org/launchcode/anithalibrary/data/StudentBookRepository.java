package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Book;
import org.launchcode.anithalibrary.model.Student;
import org.launchcode.anithalibrary.model.StudentBook;
import org.launchcode.anithalibrary.model.StudentBookId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentBookRepository extends CrudRepository<StudentBook, StudentBookId> {
    List<StudentBook> findByBook(Book book);
    List<StudentBook>findByStudent(Student student);
}
