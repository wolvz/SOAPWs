package com.multicert.exercise;

import com.multicert.exercise.customer.CustomerDAOImpl;
import com.multicert.exercise.customer.CustomerEntity;
import com.multicert.exercise.customer.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerDAOTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerDAOImpl customerDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByNif() {
        CustomerEntity ce = new CustomerEntity("Nome", "259717423", "Morada", "917762535");
        when(customerRepository.findByNif("259717423")).thenReturn(ce);
        CustomerEntity result = customerDAO.findByNif("259717423");
        assertEquals("Nome", result.getNome());
        assertEquals("259717423", result.getNif());
        assertEquals("Morada", result.getMorada());
        assertEquals("917762535", result.getTelefone());
    }

    @Test
    public void findByNome() {
        List<CustomerEntity> cList = new ArrayList<>();
        List<CustomerEntity> expectedCList = new ArrayList<>();
        cList.add(new CustomerEntity("Nome1", "259717423", "Morada1", "917762535"));
        cList.add(new CustomerEntity("Nome2", "259717424", "Morada2", "917762537"));
        cList.add(new CustomerEntity("Nome3", "259717425", "Morada3", "917762538"));
        expectedCList.add(new CustomerEntity("Nome1", "259717423", "Morada1", "917762535"));
        when(customerRepository.findByNome("Nome1")).thenReturn(expectedCList);

        List<CustomerEntity> result = customerDAO.findByNome("Nome1");
        assertEquals(1, result.size());
    }

    @Test
    public void findAll() {
        List<CustomerEntity> cList = new ArrayList<>();
        cList.add(new CustomerEntity("Nome1", "259717423", "Morada1", "917762535"));
        cList.add(new CustomerEntity("Nome2", "259717424", "Morada2", "917762537"));
        cList.add(new CustomerEntity("Nome3", "259717425", "Morada3", "917762538"));
        when(customerRepository.findAll()).thenReturn(cList);

        List<CustomerEntity> result = customerDAO.findAll();
        assertEquals(3, result.size());
    }

    @Test
    public void save() {
        CustomerEntity ce = new CustomerEntity("Nome", "259717423", "Morada", "917762535");
        when(customerRepository.save(ce)).thenReturn(ce);
        CustomerEntity result = customerDAO.save(ce);
        assertEquals("Nome", result.getNome());
        assertEquals("259717423", result.getNif());
        assertEquals("Morada", result.getMorada());
        assertEquals("917762535", result.getTelefone());
    }

    @Test
    public void deleteByNif() {
        CustomerEntity ce = new CustomerEntity("Nome", "259717423", "Morada", "917762535");
        customerDAO.deleteByNif("259717423");
        verify(customerRepository, times(1)).deleteByNif("259717423");
    }
}
