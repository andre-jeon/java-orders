package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.views.OrderCount;

import java.util.List;

public interface CustomerServices {
    List<Customer> findAllCustomerOrders();

    Customer findCustomerById(long customerID);

    List<Customer> findCustomerByKeyword(String keyword);

    List<OrderCount> findOrderCount();
}
