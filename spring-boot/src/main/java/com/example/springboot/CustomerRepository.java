package com.example.springboot;

import com.example.springboot.exceptions.CustomerNotFoundException;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findCustomerById(Integer id) throws CustomerNotFoundException;

    void deleteCustomerById(Integer id) throws CustomerNotFoundException;
}
