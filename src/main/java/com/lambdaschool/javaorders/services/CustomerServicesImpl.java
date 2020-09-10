package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices{
    @Autowired
    CustomersRepository customerrepos;

    @Override
    public List<Customer> findAllCustomerOrders() {
        List<Customer> order = new ArrayList<>();
        customerrepos.findAll().iterator().forEachRemaining(order::add);
        return order;
    }

    @Override
    public Customer findCustomerById(long customerID) {
        return customerrepos.findById(customerID)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + customerID + " Not Found!"));
    }

    @Override
    public List<Customer> findCustomerByKeyword(String keyword) {
        List<Customer> list = customerrepos.findOrderCount();
        return list;
    }
}
