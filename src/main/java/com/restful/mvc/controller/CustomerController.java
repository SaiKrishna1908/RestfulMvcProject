package com.restful.mvc.controller;

import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.api.v1.model.CustomerListDTO;
import com.restful.mvc.domain.Customer;
import com.restful.mvc.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerController.base_url)

//TODO : Implement Put Endpoint
public class CustomerController {

    private final CustomerService customerService;
    static final String base_url = "/api/v1/customers/";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<CustomerListDTO> getCustomers(){

        CustomerListDTO customerListDTO = new CustomerListDTO(customerService.getAllCustomers());
        return new ResponseEntity<CustomerListDTO>(customerListDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){

        CustomerDTO savedCustomer = customerService.postCustomer(customerDTO);

        savedCustomer.setCustomer_url(base_url+savedCustomer.getId());

        return  new ResponseEntity<CustomerDTO>(savedCustomer,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> editCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        CustomerDTO editedCustomer = customerService.editCustomer(id, customerDTO);
        editedCustomer.setCustomer_url(base_url+editedCustomer.getId());
        return new ResponseEntity<CustomerDTO>(editedCustomer, HttpStatus.OK);
    }
}
