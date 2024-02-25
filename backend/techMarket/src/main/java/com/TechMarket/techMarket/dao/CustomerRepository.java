package com.TechMarket.techMarket.dao;

import com.TechMarket.techMarket.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
