package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    public VendorDTO vendorToVendorDTO(Vendor vendor);
}
