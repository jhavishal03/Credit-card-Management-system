package com.Dark.Creditcardmanagementsystem.Repository;

import com.Dark.Creditcardmanagementsystem.model.DebitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebitTransactionRepository extends JpaRepository<DebitTransaction,Long> {
 @Query(value = "select * from debit_transaction d where d.order_id=:orderId LIMIT 1",nativeQuery = true)
 DebitTransaction findTop1ByOrderId(String orderId);
 @Query(value = "select * from debit_transaction d where d.accountid=:accId",nativeQuery = true)
 List<DebitTransaction> findByAccountId(@Param("accId") Long accId);

}
