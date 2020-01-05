package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.lg.charity.domain.entities.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findUserByEmail(String email);

    User findByEmail(String email);

    @Query(value = "SELECT * FROM users JOIN users_roles ON users.id = users_roles.user_id " +
            "WHERE users_roles.roles_id = 2", nativeQuery = true)
    List<User> findAllAdmins();

    @Query(value = "SELECT * FROM users JOIN users_roles ON users.id = users_roles.user_id " +
            "WHERE users_roles.roles_id = 1", nativeQuery = true)
    List<User> findAllUsers();

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.active = true WHERE u.id = ?1")
    void statusActiveUser(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.active = false WHERE u.id = ?1")
    void statusBlockedUser(Long id);
}
