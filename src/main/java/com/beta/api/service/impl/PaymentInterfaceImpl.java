package com.beta.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beta.api.service.PaymentInterface;

public class PaymentInterfaceImpl  implements  PaymentInterface{
    
    private  Logger  log  =  LoggerFactory.getLogger(PaymentInterfaceImpl.class);
    @Override
    public String getHello(String message) {
        log.info(message);
        return "返回给你："+message;
    }

}
