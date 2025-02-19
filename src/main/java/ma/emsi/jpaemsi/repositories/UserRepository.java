package ma.emsi.jpaemsi.repositories;

import ma.emsi.jpaemsi.entities.Role;
import ma.emsi.jpaemsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String userName);

}
