package com.multicert.exercise.customer;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findByNif(String nif);

    List<CustomerEntity> findByNome(String name);

    @Transactional
    @Modifying
    int deleteByNif(String nif);
}
