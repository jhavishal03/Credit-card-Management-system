package com.Dark.Creditcardmanagementsystem.Repository;

import com.Dark.Creditcardmanagementsystem.model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund,Long> {
}
