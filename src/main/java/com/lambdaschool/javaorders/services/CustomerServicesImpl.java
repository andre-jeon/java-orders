package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.models.Payment;
import com.lambdaschool.javaorders.repositories.CustomersRepository;
import com.lambdaschool.javaorders.repositories.PaymentRepository;
import com.lambdaschool.javaorders.views.OrderCount;
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

    @Autowired
    PaymentRepository paymentrepos;

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
        List<Customer> list = customerrepos.findByCustnameContainingIgnoringCase(keyword);
        return list;
    }

    @Override
    public List<OrderCount> findOrderCount() {
        List<OrderCount> list = customerrepos.findOrderCount();
        return list;
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0)
        {
            findCustomerByID(customer.getCustcode());
            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders())
        {
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());

            for(Payment p : o.getPayments())
            {
                Payment newPayment = paymentrepos.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found!"));

                newOrder.getPayments().add(newPayment);
            }

            newCustomer.getOrders().add(newOrder);
        }

        return customerrepos.save(newCustomer);
    }

    private void findCustomerByID(long custcode) {
    }
}
