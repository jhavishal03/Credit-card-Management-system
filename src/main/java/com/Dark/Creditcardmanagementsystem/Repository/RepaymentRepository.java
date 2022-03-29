package com.Dark.Creditcardmanagementsystem.Repository;

import com.Dark.Creditcardmanagementsystem.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment,Long> {
}
