package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByEmail(String email);
    User findByResetPasswordToken(String resetPasswordToken);
}
