package com.multicert.exercise.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerEntity findByNif(String nif) {
        return this.customerRepository.findByNif(nif);
    }

    @Override
    public List<CustomerEntity> findByNome(String name) {
        return this.customerRepository.findByNomeContainingIgnoreCase(name);
    }

    @Override
    public int deleteByNif(String nif) {
        return this.customerRepository.deleteByNif(nif);
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
