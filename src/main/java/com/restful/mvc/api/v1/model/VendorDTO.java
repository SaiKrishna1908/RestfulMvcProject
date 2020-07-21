package com.restful.mvc.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {
    private Long id;

    @ApiModelProperty(value = "Vendor's Name", notes = "Must be a String ")
    private String name;
    private String customer_url;
}
