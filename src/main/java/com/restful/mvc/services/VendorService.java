package com.restful.mvc.services;

import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getVendors();
    VendorDTO createVendor(VendorDTO vendorDTO);
    void deleteVendorById(Long id);
    VendorDTO getVendorById(Long id);
    VendorDTO editVendor(Long id, VendorDTO vendorDTO);

}
