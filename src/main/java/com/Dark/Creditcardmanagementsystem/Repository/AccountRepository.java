package com.Dark.Creditcardmanagementsystem.Repository;

import com.Dark.Creditcardmanagementsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(value = "select * from ACCOUNT s where s.userId=:userId",nativeQuery = true)
    Optional<Account> findByUserId(@Param("userId") Long userId);
}
