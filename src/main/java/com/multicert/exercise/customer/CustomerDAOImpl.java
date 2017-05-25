package com.multicert.exercise.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerEntity findByVat(String vat) {
        return this.customerRepository.findByVat(vat);
    }

    @Override
    public List<CustomerEntity> findByName(String name) {
        return this.customerRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public int deleteByVat(String vat) {
        return this.customerRepository.deleteByVat(vat);
    }

    @Override
    public List<CustomerEntity> findAll() {
        return (List<CustomerEntity>) this.customerRepository.findAll();
    }

    @Override
    public CustomerEntity save(CustomerEntity ce) {
        return this.customerRepository.save(ce);
    }

}
