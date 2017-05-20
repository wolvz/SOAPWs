package com.multicert.exercise.converters;

import ws.namespace.customerservice.datatypes.Customer;
import com.multicert.exercise.customer.CustomerEntity;

public class CustomerConverter {

    public static Customer convertCustomerEntityToCustomer(CustomerEntity ce) {
        Customer c = new Customer();
        c.setNome(ce.getNome());
        c.setNif(ce.getNif());
        c.setMorada(ce.getMorada());
        c.setTelefone(ce.getTelefone());
        return c;
    }

    public static CustomerEntity convertCustomerToCustomerEntity(Customer c) {
        CustomerEntity ce = new CustomerEntity(c.getNome(), c.getNif(), c.getMorada(), c.getTelefone());
        return ce;
    }
}
