package com.multicert.exercise.endpoint;

import com.multicert.exercise.customer.CustomerDAOImpl;
import com.multicert.exercise.customer.CustomerEntity;
import ws.namespace.customerservice.CustomerException;
import ws.namespace.customerservice.datatypes.ArrayOfCustomer;
import ws.namespace.customerservice.datatypes.Customer;
import ws.namespace.customerservice.general.AddCustomerRequest;
import ws.namespace.customerservice.general.AddCustomerReturn;
import ws.namespace.customerservice.general.CustomerRequest;
import ws.namespace.customerservice.general.CustomerReturn;
import ws.namespace.customerservice.general.CustomersRequest;
import ws.namespace.customerservice.general.CustomersReturn;
import ws.namespace.customerservice.general.DeleteCustomerRequest;
import ws.namespace.customerservice.general.DeleteCustomerReturn;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceController {

    @Autowired
    private CustomerDAOImpl customerDao;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerReturn getCustomerByVAT(CustomerRequest customerRequest) throws CustomerException {

        CustomerReturn cr = new CustomerReturn();
        String vat = customerRequest.getVAT();

        boolean validVat = true;
        /* to be implemented */
        
        if (!validVat) {
            cr.setSuccess(false);
            cr.setErrorCode(100);//invalid vat
        } else {
            CustomerEntity ce = customerDao.findByVat(vat);

            if (ce != null) {
                Customer c = this.convertToDto(ce);
                cr.setSuccess(true);
                cr.setCustomerResult(c);
            } else {
                cr.setSuccess(false);
                cr.setErrorCode(101);//user not found
            }
        }

        return cr;
    }

    public CustomersReturn getCustomersByName(CustomersRequest customersRequest) {

        String name = customersRequest.getName();
        List<CustomerEntity> ceList = customerDao.findByName(name);
        CustomersReturn cr = new CustomersReturn();
        ArrayOfCustomer ac = new ArrayOfCustomer();
        for (CustomerEntity ce : ceList) {
            Customer c = this.convertToDto(ce);
            ac.getCustomer().add(c);
        }

        cr.setSuccess(true);
        cr.setCustomersResult(ac);

        return cr;
    }

    public CustomersReturn getCustomers() {

        List<CustomerEntity> ceList = (List<CustomerEntity>) customerDao.findAll();
        CustomersReturn cr = new CustomersReturn();
        ArrayOfCustomer ac = new ArrayOfCustomer();
        for (CustomerEntity ce : ceList) {
            Customer c = this.convertToDto(ce);
            ac.getCustomer().add(c);
        }

        cr.setSuccess(true);
        cr.setCustomersResult(ac);

        return cr;
    }

    AddCustomerReturn addCustomer(AddCustomerRequest addCustomerRequest) {

        Customer c = addCustomerRequest.getCustomer();
        CustomerEntity ce = this.convertToEntity(c);
        AddCustomerReturn acr = new AddCustomerReturn();
        try {
            acr.setSuccess(true);
            CustomerEntity res = this.customerDao.save(ce);
        } catch (Exception e) {
            //save was not possible due to validations (unique vat constraint, for example)
            acr.setSuccess(false);
            acr.setErrorCode(102);
            acr.setErrorMessage(e.getMessage());//this error message must be adapted to a friendly message
        }

        return acr;
    }

    DeleteCustomerReturn deleteCustomer(DeleteCustomerRequest deleteCustomerRequest) {

        String vat = deleteCustomerRequest.getVAT();
        int affectedRows = this.customerDao.deleteByVat(vat);
        DeleteCustomerReturn dcr = new DeleteCustomerReturn();

        if (affectedRows > 1) {
            dcr.setSuccess(true);
        } else {
            dcr.setSuccess(false);
            dcr.setErrorCode(103);
        }

        return dcr;
    }

    private Customer convertToDto(CustomerEntity customerEntity) {
        Customer customer = modelMapper.map(customerEntity, Customer.class);
        return customer;
    }

    private CustomerEntity convertToEntity(Customer customer) {
        CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);
        return customerEntity;
    }
}
