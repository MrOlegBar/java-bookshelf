package ru.codeinside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.codeinside.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}