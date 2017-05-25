package com.multicert.exercise.customer;

import java.util.List;

public interface CustomerDAO {

    CustomerEntity findByVat(String vat);

    List<CustomerEntity> findByName(String name);

    List<CustomerEntity> findAll();

    CustomerEntity save(CustomerEntity ce);

    int deleteByVat(String vat);
}
