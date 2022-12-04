package com.example.springboot;

import ch.qos.logback.classic.pattern.LevelConverter;
import com.example.springboot.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class DemoController {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestParam String name, @RequestParam String surname) {
        Customer customer = new Customer(name, surname);
        customerRepository.save(customer);

        logger.log(Level.INFO, "New customer has been added - Customer id:" + customer.getId().toString());
        return "New customer has been added.";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        Customer cust = null;
        try {
            cust = customerRepository.findCustomerById(id);
        } catch (CustomerNotFoundException exception) {
            logger.log(Level.INFO, "Customer " +  id.toString() + " could not be found!");
        }
        return cust;
    }

    @PostMapping("/delete/{id}")
//    @RequestMapping(value = "/delete/{id}")
    public String deleteCustomerById(@PathVariable Integer id) {
        try {
            customerRepository.deleteCustomerById(id);
        } catch (CustomerNotFoundException e) {
            logger.log(Level.INFO, "Customer " + id.toString() + " could not be deleted!");
            return "Customer " + id.toString() + " could not be deleted!";
        }
        return "Customer " + id.toString() + " was deleted";
    }
}
