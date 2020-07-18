package com.restful.mvc.controller;

import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;
import com.restful.mvc.services.VendorService;
import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.restful.mvc.utils.RequestUtils.asJsonString;
class VendorControllerTest {


    public static final String FIRSTNAME = "Hello";
    public static final String FIRSTNAME1 = "World";
    private final String baseUrl = "/api/v1/vendors/";
    MockMvc mockMvc;

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
        vendorController = new VendorController(vendorService);
    }

    @Test
    void getAllVendors() throws Exception {

        //given
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(FIRSTNAME);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setName(FIRSTNAME1);

        List<VendorDTO> vendorDTOS = new ArrayList<>();
        vendorDTOS.add(vendorDTO1);
        vendorDTOS.add(vendorDTO2);


        //when
        when(vendorService.getVendors()).thenReturn(vendorDTOS);
    mockMvc.perform(get(baseUrl+"/").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.vendors",hasSize(2)));
    }

    @Test
    void createVendor() throws Exception {
        //given

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(FIRSTNAME);

        when(vendorService.createVendor(any())).thenReturn(vendorDTO);

        mockMvc.perform(post(baseUrl+"/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(vendorDTO)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteVendor() throws Exception {

        mockMvc.perform(delete(baseUrl+"1")).andExpect(status().isOk());

        verify(vendorService, times(1)).deleteVendorById(anyLong());
    }

    @Test
    void getVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(FIRSTNAME);
        vendorDTO.setId(1L);

        when(vendorService.getVendorById(any())).thenReturn(vendorDTO);

        mockMvc.perform(get(baseUrl+"1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$.name", equalTo(FIRSTNAME)));
    }

    @Test
    void editVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(FIRSTNAME);
        vendorDTO.setId(1L);

        VendorDTO edited  = new VendorDTO();
        edited.setName(FIRSTNAME1);
        edited.setId(vendorDTO.getId());
        //when
        when(vendorService.editVendor(anyLong(), any())).thenReturn(edited);

        mockMvc.perform(put(baseUrl+"1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(edited)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }
}