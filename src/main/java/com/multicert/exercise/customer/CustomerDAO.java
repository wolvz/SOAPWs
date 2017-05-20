package com.multicert.exercise.customer;

import java.util.List;

public interface CustomerDAO {

    CustomerEntity findByNif(String nif);

    List<CustomerEntity> findByNome(String name);

    List<CustomerEntity> findAll();

    CustomerEntity save(CustomerEntity ce);

    int deleteByNif(String nif);
}
