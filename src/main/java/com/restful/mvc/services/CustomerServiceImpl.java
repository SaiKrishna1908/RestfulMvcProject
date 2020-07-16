package com.restful.mvc.services;

import com.restful.mvc.api.v1.Mapper.CustomerDTOMapper;
import com.restful.mvc.api.v1.Mapper.CustomerMapper;
import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.domain.Customer;
import com.restful.mvc.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements  CustomerService{


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerDTOMapper customerDTOM;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerDTOMapper customerDTOM) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerDTOM = customerDTOM;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO postCustomer(CustomerDTO customerDTO) {


        try{
            Customer customer = customerDTOM.customerDTOToCustomer(customerDTO);

            CustomerDTO savedCustomer = customerMapper.customerToCustomerDTO(customerRepository.save(customer));


            return savedCustomer;
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error saving customer...");
        }

        return null;
    }

    @Override
    public void deleteCustomerById(Long id) {
        try{
            customerRepository.deleteById(id);
        }
        catch (NoSuchElementException e){
            log.error("Customer Does not exist");
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error deleting customer..."+id);
        }

    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(!customerOptional.isPresent()){
            log.error("No Customer with id "+id+" found");
            return  null;
        }
        else {
            Customer customer = customerOptional.get();
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            return customerDTO;
        }

    }

    @Override
    public CustomerDTO editCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(!customerOptional.isPresent()){
            log.error("Customer Not Found");
            throw new NoSuchElementException(id+" Not found");

        }
        else {
                Customer customer = customerOptional.get();
                customer.setLastname(customerDTO.getLastname());
                customer.setFirstname(customerDTO.getFirstname());

                Customer editedcustomer = customerRepository.save(customer);

                return customerMapper.customerToCustomerDTO(editedcustomer);
        }
    }


}
