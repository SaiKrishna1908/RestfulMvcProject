package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorDTOMapperTest {

    public static final String FIRSTNAME = "Johnny";
    VendorDTOMapper vendorDTOMapper = VendorDTOMapper.INSTANCE;

    @Test
    void vendorDTOToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(FIRSTNAME);


        Vendor vendor=  vendorDTOMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(FIRSTNAME, vendor.getName());
    }
}