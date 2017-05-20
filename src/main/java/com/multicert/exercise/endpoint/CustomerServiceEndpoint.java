package com.multicert.exercise.endpoint;

import ws.namespace.customerservice.CustomerException;
import ws.namespace.customerservice.CustomerService;
import ws.namespace.customerservice.general.AddCustomerRequest;
import ws.namespace.customerservice.general.AddCustomerReturn;
import ws.namespace.customerservice.general.CustomerRequest;
import ws.namespace.customerservice.general.CustomerReturn;
import ws.namespace.customerservice.general.CustomersRequest;
import ws.namespace.customerservice.general.CustomersReturn;
import ws.namespace.customerservice.general.DeleteCustomerRequest;
import ws.namespace.customerservice.general.DeleteCustomerReturn;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceEndpoint implements CustomerService {

    @Autowired
    private CustomerServiceController customerServiceController;

    @Override
    public CustomerReturn getCustomerByNIF(CustomerRequest customerRequest) throws CustomerException {
        return this.customerServiceController.getCustomerByNIF(customerRequest);
    }

    @Override
    public CustomersReturn getCustomersByName(CustomersRequest customersRequest) throws CustomerException {
        return this.customerServiceController.getCustomersByName(customersRequest);
    }

    @Override
    public CustomersReturn getCustomers() throws CustomerException {
        return this.customerServiceController.getCustomers();
    }

    @Override
    public AddCustomerReturn addCustomer(AddCustomerRequest addCustomerRequest) throws CustomerException {
        return this.customerServiceController.addCustomer(addCustomerRequest);
    }

    @Override
    public DeleteCustomerReturn deleteCustomer(DeleteCustomerRequest deleteCustomerRequest) throws CustomerException {
        return this.customerServiceController.deleteCustomer(deleteCustomerRequest);
    }

}
