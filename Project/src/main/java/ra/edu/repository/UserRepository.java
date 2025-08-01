package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
