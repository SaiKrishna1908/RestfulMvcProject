package com.restful.mvc.controller;

import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.domain.Customer;
import com.restful.mvc.repository.CustomerRepository;
import com.restful.mvc.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.restful.mvc.utils.RequestUtils.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    public static final String FIRSTNAME = "Jerry";
    public static final String LASTNAME = "Jack";
    public static final long ID = 1L;
    public static final String FIRSTNAME1 = "Mika";
    public static final String LASTNAME1 = "Maker";
    public static final long ID1 = 2L;
    @Mock
    CustomerService customerService;
    CustomerController customerController;
    MockMvc mockMvc;

    static final String base_url = "/api/v1/customers/";


    @BeforeEach
    public void init() {

        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }


    @Test
    void getCustomers() throws Exception {
        CustomerDTO jack  =new CustomerDTO();

        jack.setLastname(FIRSTNAME);
        jack.setFirstname(LASTNAME);
        jack.setId(ID);

        CustomerDTO sherry = new CustomerDTO();
        sherry.setFirstname(FIRSTNAME1);
        sherry.setLastname(LASTNAME1);
        sherry.setId(ID1);

        List<CustomerDTO> customers = new ArrayList<>();
        customers.add(jack);
        customers.add(sherry);

        when(customerService.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(get(base_url+"/", MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));

    }

    @Test
    void createCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);
        customerDTO.setId(ID);

        when(customerService.postCustomer(any())).thenReturn(customerDTO);

        mockMvc.perform(post(base_url+"/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname",equalTo(FIRSTNAME)));
    }

    @Test
    void deleteCustomer() throws Exception {

        mockMvc.perform(delete(base_url+"/1"));
        verify(customerService,times(1)).deleteCustomerById(anyLong());
    }

    @Test
    void editCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(LASTNAME1);
        customerDTO.setFirstname(FIRSTNAME1);
        customerDTO.setId(ID1);

        CustomerDTO edited = customerDTO;
        edited.setLastname(LASTNAME);
        when(customerService.editCustomer(anyLong(), any())).thenReturn(edited);

        mockMvc.perform(put(base_url+"2").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO))).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)));
    }
}