package com.ebay.calculator.demo.model;

import lombok.Data;
import java.util.List;

@Data
public class ChainCalculationRequest {
    private double initialValue;
    private List<String> operations;
    private List<Double> operands;
}