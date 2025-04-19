package com.application.apa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.application.apa.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Long>  {

}
