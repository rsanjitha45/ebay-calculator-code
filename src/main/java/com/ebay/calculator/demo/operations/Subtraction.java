package com.ebay.calculator.demo.operations;

import com.ebay.calculator.demo.strategy.OperationStrategy;

import java.math.BigDecimal;

/**
 * Represents the subtraction operation.
 * Subtracts the second number from the first and returns the result.
 */
public class Subtraction implements OperationStrategy {

    @Override
    public Number apply(final Number num1, final Number num2) {
        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("Null values are not allowed in subtraction.");
        }

        if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
            return toBigDecimal(num1).subtract(toBigDecimal(num2));
        }

        if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() - num2.intValue();
        }

        return num1.doubleValue() - num2.doubleValue();
    }

    private BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        return BigDecimal.valueOf(number.doubleValue());
    }

}
