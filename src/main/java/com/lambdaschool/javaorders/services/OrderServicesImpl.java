package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    OrdersRepository orderrepos;

    @Override
    public Order findOrderById(long value) {
        return orderrepos.findById(value)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + value + " Was not found!"));
    }
}
