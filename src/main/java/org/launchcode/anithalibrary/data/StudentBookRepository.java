package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Student;
import org.launchcode.anithalibrary.model.StudentBook;
import org.launchcode.anithalibrary.model.StudentBookId;
import org.springframework.data.repository.CrudRepository;

public interface StudentBookRepository extends CrudRepository<StudentBook, StudentBookId> {
}
