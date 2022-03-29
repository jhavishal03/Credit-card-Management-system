package com.Dark.Creditcardmanagementsystem.Repository;

import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebitTransactionRepository extends JpaRepository<DebitTransaction,Long> {
 DebitTransaction findTop1ByOrderId(String orderId);
}
