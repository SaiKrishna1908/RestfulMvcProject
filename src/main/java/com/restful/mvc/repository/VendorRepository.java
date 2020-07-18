package com.restful.mvc.repository;

import com.restful.mvc.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO: Add Integeration Tests
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
