package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    List<Student> findByLastname(String lastName);
    Student findByContactEmail(String email);
}