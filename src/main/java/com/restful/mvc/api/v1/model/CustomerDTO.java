package com.restful.mvc.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode
public class CustomerDTO  {

    private Long id;

    @ApiModelProperty(value = "This is the first name" ,required = true)
    private String firstname;

    @ApiModelProperty(value = "This is the last name", required = false)
    private String lastname;


    private String customer_url;
}
