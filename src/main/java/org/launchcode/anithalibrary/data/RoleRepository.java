package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
