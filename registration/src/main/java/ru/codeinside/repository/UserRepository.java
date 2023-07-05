package ru.codeinside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.codeinside.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);

    User findByEmailIgnoreCaseAndPasswordEquals(String email, String password);
}