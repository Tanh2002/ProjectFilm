package repositories;

import entities.RoleUser;
import models.ERoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleUserRepository extends JpaRepository<RoleUser, Integer> {
    Optional<RoleUser> findByName(ERoleUser name);

}
