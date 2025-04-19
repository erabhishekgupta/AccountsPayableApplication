package com.application.apa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.application.apa.model.Customer;



public interface CustomerRepository extends JpaRepository<Customer,Integer>{

	Optional<Customer> findById(Long id);

	void deleteById(Long id);

}
