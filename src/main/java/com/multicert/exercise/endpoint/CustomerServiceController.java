package com.multicert.exercise.endpoint;

import com.multicert.exercise.customer.CustomerDAOImpl;
import com.multicert.exercise.customer.CustomerEntity;
import com.multicert.exercise.converters.CustomerConverter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceController {

    @Autowired
    private CustomerDAOImpl customerDao;

    public CustomerServiceController() {
    }

    public CustomerReturn getCustomerByNIF(CustomerRequest customerRequest) throws CustomerException {
        String nif = customerRequest.getNIF();
        CustomerEntity ce = customerDao.findByNif(nif);
        CustomerReturn cr = new CustomerReturn();

        if (ce != null) {
            Customer c = CustomerConverter.convertCustomerEntityToCustomer(ce);
            cr.setCustomerResult(c);
        }
        cr.setSuccess(true);
        return cr;
    }

    public CustomersReturn getCustomersByName(CustomersRequest customersRequest) {

        String name = customersRequest.getName();
        List<CustomerEntity> ceList = customerDao.findByNome(name);
        CustomersReturn cr = new CustomersReturn();
        ArrayOfCustomer ac = new ArrayOfCustomer();
        for (CustomerEntity ce : ceList) {
            Customer c = CustomerConverter.convertCustomerEntityToCustomer(ce);
            ac.getCustomer().add(c);
        }

        cr.setCustomersResult(ac);
        cr.setSuccess(true);
        return cr;
    }

    public CustomersReturn getCustomers() {
        List<CustomerEntity> ceList = (List<CustomerEntity>) customerDao.findAll();
        CustomersReturn cr = new CustomersReturn();
        ArrayOfCustomer ac = new ArrayOfCustomer();
        for (CustomerEntity ce : ceList) {
            Customer c = CustomerConverter.convertCustomerEntityToCustomer(ce);
            ac.getCustomer().add(c);
        }

        cr.setCustomersResult(ac);
        cr.setSuccess(true);
        return cr;
    }

    AddCustomerReturn addCustomer(AddCustomerRequest addCustomerRequest) {

        Customer c = addCustomerRequest.getCustomer();
        CustomerEntity ce = CustomerConverter.convertCustomerToCustomerEntity(c);
        AddCustomerReturn acr = new AddCustomerReturn();
        try {
            CustomerEntity res = this.customerDao.save(ce);
            acr.setSuccess(true);
        } catch (Exception e) {
            acr.setResponseText(e.getClass().getSimpleName());
            acr.setSuccess(false);
        }

        return acr;
    }

    DeleteCustomerReturn deleteCustomer(DeleteCustomerRequest deleteCustomerRequest) {

        String nif = deleteCustomerRequest.getNIF();
        int res = this.customerDao.deleteByNif(nif);
        DeleteCustomerReturn dcr = new DeleteCustomerReturn();
        dcr.setSuccess(true);
        if (res == 0) {
            dcr.setResponseText("No records were deleted");
        }
        return dcr;
    }
}
