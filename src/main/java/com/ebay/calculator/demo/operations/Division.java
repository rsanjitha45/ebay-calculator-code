package com.ebay.calculator.demo.operations;

import com.ebay.calculator.demo.strategy.OperationStrategy;

import java.math.BigDecimal;

/**
 * Represents the division operation.
 * Divides the first number by the second and returns the result.
 * Throws an {@link ArithmeticException} if the second number is zero.
 */
public class Division implements OperationStrategy {

    @Override
    public Number apply(final Number num1, final Number num2) {
        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("Null values are not allowed in division.");
        }

        if (num2.doubleValue() == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
            return toBigDecimal(num1).divide(toBigDecimal(num2), BigDecimal.ROUND_HALF_EVEN);
        }

        if (num1 instanceof Integer && num2 instanceof Integer) {
            return num1.intValue() / num2.intValue();
        }

        return num1.doubleValue() / num2.doubleValue();
    }

    private BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        return BigDecimal.valueOf(number.doubleValue());
    }

}
