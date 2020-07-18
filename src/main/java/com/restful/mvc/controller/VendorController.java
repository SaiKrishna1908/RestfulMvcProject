package com.restful.mvc.controller;


import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.api.v1.model.VendorListDTO;
import com.restful.mvc.domain.Vendor;
import com.restful.mvc.services.VendorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors/")


public class VendorController {

    private final VendorService vendorService;

    private final static String baseUrl = "/api/v1/vendors/";

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @GetMapping("/")
    public VendorListDTO getAllVendors(){
        VendorListDTO vendorListDTO = new VendorListDTO(vendorService.getVendors());
        vendorListDTO.getVendors().forEach(vendorDTO -> {
            vendorDTO.setCustomer_url(baseUrl+vendorDTO.getId());
        });

        return vendorListDTO;
    }

    @PostMapping("/")
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO){
        VendorDTO savedVendor =  vendorService.createVendor(vendorDTO);
        savedVendor.setCustomer_url(baseUrl+savedVendor.getId());
        return savedVendor;
    }

    @DeleteMapping("{id}")
    public void deleteVendor(@PathVariable  Long id){
        vendorService.deleteVendorById(id);
    }

    @GetMapping("{id}")
    public VendorDTO getVendorById(@PathVariable Long id){
        VendorDTO vendorDTO = vendorService.getVendorById(id);
        vendorDTO.setCustomer_url(baseUrl+vendorDTO.getId());
        return vendorDTO;
    }

    @PutMapping("{id}")
    public VendorDTO editVendorById(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        VendorDTO savedVendor = vendorService.editVendor(id, vendorDTO);
        savedVendor.setCustomer_url(baseUrl+savedVendor.getId());
        return savedVendor;
    }

}
