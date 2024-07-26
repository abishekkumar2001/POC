package com.soc.soar.controller;

import com.soc.soar.service.ArithmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArithmeticController {

    @Autowired
    ArithmeticService arithmeticService;

    @GetMapping("/divide/{dividend}/{divisor}")
    public Integer divideFunction(@PathVariable int dividend, @PathVariable int divisor){
        if(divisor == 0){
            throw new ArithmeticException("A number cannot be divided by zero");
        }
        return arithmeticService.divideFunction(dividend,divisor);
    }

}
