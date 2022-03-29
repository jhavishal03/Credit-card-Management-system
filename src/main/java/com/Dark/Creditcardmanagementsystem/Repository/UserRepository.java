package com.Dark.Creditcardmanagementsystem.Repository;

import com.Dark.Creditcardmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailId(String email);

    List<User> findByFirstNameIgnoreCase(String fistName);

    List<User> findByLastNameIgnoreCase(String lastName);
}
