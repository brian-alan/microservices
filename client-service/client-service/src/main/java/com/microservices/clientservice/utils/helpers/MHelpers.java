package com.microservices.clientservice.utils.helpers;

import org.modelmapper.ModelMapper;

public class MHelpers {
    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
