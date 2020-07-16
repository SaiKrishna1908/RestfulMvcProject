package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOMapperTest {

    public static final String FIRSTNAME = "Julie";
    public static final String LASTNAME = "angela";
    public static final long ID = 1L;
    CustomerDTOMapper mapper = CustomerDTOMapper.INSTANCE;


    @Test
    void customerDTOToCustomer() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);

        Customer customer = mapper.customerDTOToCustomer(customerDTO);

        assertEquals(ID, customer.getId());
    }
}