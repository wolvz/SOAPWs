package com.multicert.exercise.configuration;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.namespace.customerservice.CustomerService;
import com.multicert.exercise.endpoint.CustomerServiceEndpoint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Configuration
public class WebServiceConfiguration {

    @Autowired
    private Bus bus;

    @Autowired
    private Environment env;

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceEndpoint();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, customerService());
        endpoint.publish(env.getProperty("wsdlVersion"));
        //endpoint.setWsdlLocation("Customer1.0.wsdl");
        return endpoint;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
