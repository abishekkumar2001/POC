package com.soc.soar.service;

import org.springframework.stereotype.Service;

@Service
public class ArithmeticService {

    public Integer divideFunction(int dividend, int divisor) {
        return dividend/divisor;
    }

}