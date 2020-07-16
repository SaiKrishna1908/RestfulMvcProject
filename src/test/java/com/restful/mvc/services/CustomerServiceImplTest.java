package com.restful.mvc.services;

import com.restful.mvc.api.v1.Mapper.CustomerDTOMapper;
import com.restful.mvc.api.v1.Mapper.CustomerMapper;
import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.domain.Customer;
import com.restful.mvc.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    public static final String LASTNAME1 = "Sins";
    public static final String FIRSTNAME1 = "Johnny";
    public static final long ID1 = 1L;
    public static final String LASTNAME2 = "Riley";
    public static final String FIRSTNAME2 = "Maddy";
    public static final long ID2 = 2L;
    CustomerService customerService;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    CustomerDTOMapper customerDTOMapper= CustomerDTOMapper.INSTANCE;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper, customerDTOMapper);
    }

    @Test
    void getAllCustomers() {
        //given
        Customer johnny = new Customer();
        johnny.setId(ID1);
        johnny.setLastname(FIRSTNAME1);
        johnny.setFirstname(LASTNAME1);

        Customer maddy = new Customer();
        maddy.setId(ID2);
        maddy.setFirstname(FIRSTNAME2);
        maddy.setLastname(LASTNAME2);

        List<Customer> customers = new ArrayList<>();
        customers.add(johnny);
        customers.add(maddy);

        //when
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers() ;

        assertEquals(customers.size(), customerDTOS.size());
    }

    @Test
    void postCustomer() {
        //given
        CustomerDTO johnny = new CustomerDTO();
        johnny.setFirstname(FIRSTNAME1);
        johnny.setLastname(LASTNAME1);
//        johnny.setId(ID1);

        //when
        when(customerRepository.save(any())).thenReturn(customerDTOMapper.customerDTOToCustomer(johnny));

        CustomerDTO savedCustomer = customerService.postCustomer(johnny);

        assertEquals(johnny.getFirstname(), savedCustomer.getFirstname());
        assertEquals(johnny.getLastname(), savedCustomer.getLastname());
    }

    @Test
    public void deleteCustomerById() {
        //given

        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME1);
        customer.setLastname(LASTNAME1);
        customer.setId(ID1);

        Customer customer1 = new Customer();
        customer1.setId(ID2);
        customer1.setLastname(LASTNAME2);
        customer1.setFirstname(FIRSTNAME2);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);



    }

    @Test
    void getCustomerById() {
        //given
        Customer johnny = new Customer();
        johnny.setId(ID1);
        johnny.setFirstname(FIRSTNAME1);
        johnny.setLastname(LASTNAME1);

        when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(johnny));

        CustomerDTO customerDTO = customerService.getCustomerById(1L);
        verify(customerRepository, times(1)).findById(any());
    }

    @Test
    void editCustomer() {
        //given
        Customer johnny = new Customer();
        johnny.setId(ID1);
        johnny.setFirstname(FIRSTNAME1);
        johnny.setLastname(LASTNAME1);

        when(customerRepository.findById((anyLong()))).thenReturn(Optional.of(johnny));

        when(customerRepository.save(any())).thenAnswer(
                (Customer) ->{
                    johnny.setLastname(LASTNAME2);
                    return null;
                }
        ).thenReturn(johnny);

        CustomerDTO customerDTO = customerService.getCustomerById(1L);
        verify(customerRepository, times(2));


    }
}