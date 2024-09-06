package com.ebay.calculator.demo.model;

import lombok.Data;

@Data
public class CalculationRequest {
    private String operation;
    private Number num1;
    private Number num2;
}