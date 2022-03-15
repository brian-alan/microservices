package com.microservices.paymentservice.utils.helpers;

import org.modelmapper.ModelMapper;

public class MHelper {
    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
