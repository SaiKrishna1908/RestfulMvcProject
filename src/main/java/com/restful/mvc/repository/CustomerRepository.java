package com.restful.mvc.repository;

import com.restful.mvc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO: Add Integeration test's
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
