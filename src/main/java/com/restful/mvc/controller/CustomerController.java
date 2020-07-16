package com.restful.mvc.controller;

import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/")

//TODO : Write Test's for Controller's
public class CustomerController {

    private final CustomerService customerService;
    private static final String base_url = "/api/v1/customers/";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> getCustomers(){

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        customerDTOS.forEach(customerDTO -> customerDTO.setCustomer_url(base_url+customerDTO.getId()));

        return new ResponseEntity<List<CustomerDTO>>(customerDTOS, HttpStatus.OK);
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
