package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    public static final String FIRSTNAME = "johnny";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setName(FIRSTNAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(FIRSTNAME, vendorDTO.getName());
    }
}