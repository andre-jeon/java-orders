package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderServices orderServices;

    // http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{value}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long value) {
        Order order =orderServices.findOrderById(value);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // POST
    // http://localhost:2019/orders/order

    // PUT
    // http://localhost:2019/orders/order/63

    // DELETE
    // http://localhost:2019/orders/order/58

}
