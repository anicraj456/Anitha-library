package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Student;
import org.launchcode.anithalibrary.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByLastname(String lastName);
    Student findByContactEmail(String email);
}