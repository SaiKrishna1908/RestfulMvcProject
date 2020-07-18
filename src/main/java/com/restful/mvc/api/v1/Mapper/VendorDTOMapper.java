package com.restful.mvc.api.v1.Mapper;


import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface VendorDTOMapper {
    static  VendorDTOMapper INSTANCE = Mappers.getMapper(VendorDTOMapper.class);

    public Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
