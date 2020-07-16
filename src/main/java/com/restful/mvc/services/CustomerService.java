package com.restful.mvc.services;

import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO postCustomer(CustomerDTO customerDTO);

    void deleteCustomerById(Long id);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO editCustomer(Long id, CustomerDTO customerDTO);


}
