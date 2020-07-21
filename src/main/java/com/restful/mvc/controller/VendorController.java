package com.restful.mvc.controller;


import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.api.v1.model.VendorListDTO;
import com.restful.mvc.domain.Vendor;
import com.restful.mvc.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.jfr.ContentType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors/")
@Api
@ApiResponses(value = {@ApiResponse(code = 200, message = "" ), @ApiResponse(code = 404, message = "Not Found")})
public class VendorController {

    private final VendorService vendorService;

    private final static String baseUrl = "/api/v1/vendors/";

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @ApiOperation(value = "Get all vendor's", notes = "Name's and their Url are returned")
    @GetMapping("/")
    @ApiResponse(code = 200 , message = "")
    public VendorListDTO getAllVendors(){
        VendorListDTO vendorListDTO = new VendorListDTO(vendorService.getVendors());
        vendorListDTO.getVendors().forEach(vendorDTO -> {
            vendorDTO.setCustomer_url(baseUrl+vendorDTO.getId());
        });

        return vendorListDTO;
    }

    @ApiOperation(value = "Create a New Vendor", notes = "Vendor must have a name")
    @PostMapping("/")
    @ApiResponse(code = 200, message = "")
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO){
        VendorDTO savedVendor =  vendorService.createVendor(vendorDTO);
        savedVendor.setCustomer_url(baseUrl+savedVendor.getId());
        return savedVendor;
    }

    @ApiOperation(value = "Delete a existing Vendor", notes = "Valid id must be passed")
    @DeleteMapping("{id}")
    @ApiResponse(code = 200, message = "")
    public void deleteVendor(@PathVariable  Long id){
        vendorService.deleteVendorById(id);
    }

    @ApiOperation(value = "Get Vendor by Id", notes = "Id must be a valid Number")
    @GetMapping("{id}")
    @ApiResponse(code = 200, message = "")
    public VendorDTO getVendorById(@PathVariable Long id){
        VendorDTO vendorDTO = vendorService.getVendorById(id);
        vendorDTO.setCustomer_url(baseUrl+vendorDTO.getId());
        return vendorDTO;
    }

    @ApiOperation(value = "Edit a Vendor Detail's", notes = "Requires ID and editing details")
    @ApiResponse(code = 200, message = "Successfully updated entry")
    @PutMapping("{id}")
    public VendorDTO editVendorById(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        VendorDTO savedVendor = vendorService.editVendor(id, vendorDTO);
        savedVendor.setCustomer_url(baseUrl+savedVendor.getId());
        return savedVendor;
    }

}
