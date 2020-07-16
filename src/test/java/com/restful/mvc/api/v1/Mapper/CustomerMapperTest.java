package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerMapperTest {

    public static final long ID = 2L;
    public static final String FIRSTNAME = "chaitu";
    public static final String LASTNAME = "mudddi";

    private CustomerMapper mapper = CustomerMapper.INSTANCE;


    @BeforeEach
    void setUp() {

    }

    @Test
    void customerToCustomerDTO() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);

//        assertEquals(ID,Long.valueOf(customerDTO.getId()));
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }
}