package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.CustomerDTO;
import com.restful.mvc.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper  {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
