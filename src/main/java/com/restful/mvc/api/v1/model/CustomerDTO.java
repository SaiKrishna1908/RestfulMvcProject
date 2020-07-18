package com.restful.mvc.api.v1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode
public class CustomerDTO  {

    private Long id;
    private String firstname;
    private String lastname;
    private String customer_url;
}
