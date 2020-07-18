package com.restful.mvc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestUtils {

    public static String asJsonString(final Object object){
        try{
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonContent = objectMapper.writeValueAsString(object);

            log.debug(jsonContent);
            return jsonContent;
        }
        catch(Exception e){
           throw new RuntimeException();
        }
    }
}
