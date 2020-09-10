package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> findAllCustomerOrders() {
        List<Customer> orderList = customerServices.findAllCustomerOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    // PathVariable is only for {params}

    // http://localhost:2019/customers/customer/7
    // http://localhost:2019/customers/customer/77
    @GetMapping(value = "customer/{customerID}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long customerID) {
        Customer c = customerServices.findCustomerById(customerID);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/mes
    // http://localhost:2019/customers/namelike/cin
    @GetMapping(value = "namelike/{keyword}", produces = "application/json")
    public ResponseEntity<?> findCustomerByName(@PathVariable String keyword) {
        List<Customer> customer = customerServices.findCustomerByKeyword(keyword);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
