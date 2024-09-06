package com.ebay.calculator.demo.utils;

import com.ebay.calculator.demo.enums.Operation;
import com.ebay.calculator.demo.model.ChainCalculationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CalculatorValidator {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorValidator.class);


    /**
     * Validates if the provided operation is part of the supported {@link Operation}.
     * If the operation is not valid, throws an {@link IllegalArgumentException}.
     *
     * @param operation The operation provided by the user
     * @return A valid {@link Operation} if the operation is valid
     * @throws IllegalArgumentException if the operation is invalid
     */
    public Operation validateOperation(String operation) {
        try {
            return Operation.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported operation: " + operation +
                    ". Supported operations are: " + Arrays.toString(Operation.values()));
        }
    }

    /**
     * Validates that the operands are not null.
     *
     * @param num1 the first operand
     * @param num2 the second operand
     * @throws IllegalArgumentException if any operand is null
     */
    public void validateOperands(Number num1, Number num2) {
        if (num1 == null || num2 == null) {
            logger.error("Invalid operands: num1 or num2 is null.");
            throw new IllegalArgumentException("Operands must not be null.");
        }
    }


    /**
     * /**
     * Validates that the divisor is not zero for division operations.
     * @param num1 the dividend (the number to be divided)
     * @param num2 the divisor (the number by which to divide)
     * @throws ArithmeticException if the divisor is zero
     */
    public void validateDivision(Number num1, Number num2) {
        if (num2 == null) {
            logger.error("Division attempt with a null divisor. num1={}, num2=null", num1);
            throw new IllegalArgumentException("Divisor cannot be null.");
        }

        if (num2.doubleValue() == 0.0) {
            logger.error("Division by zero attempt with num1={} and num2={}", num1, num2);
            throw new ArithmeticException("Cannot divide by zero.");
        }
    }

    /**
     * Validates the chain calculation request to ensure all operations and operands are valid.
     *
     * @param request the request object containing initial value, operations, and operands of class ChainCalculationRequest
     * @throws IllegalArgumentException if validation fails
     */
    public void validateChainRequest(ChainCalculationRequest request) {
        if (request.getInitialValue() == null) {
            logger.error("Initial value is missing.");
            throw new IllegalArgumentException("Initial value cannot be null.");
        }

        List<String> operations = request.getOperations();
        List<Number> operands = request.getOperands();

        if (operations == null || operations.isEmpty()) {
            logger.error("Operations list is missing or empty.");
            throw new IllegalArgumentException("Operations list cannot be null or empty.");
        }

        if (operands == null || operands.isEmpty()) {
            logger.error("Operands list is missing or empty.");
            throw new IllegalArgumentException("Operands list cannot be null or empty.");
        }

        if (operations.size() != operands.size()) {
            logger.error("Mismatch between the number of operations and operands: operations={}, operands={}", operations.size(), operands.size());
            throw new IllegalArgumentException("The number of operations and operands must match.");
        }

        for (String operation : operations) {
            try {
                Operation.valueOf(operation.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid operation found: {}", operation);
                throw new IllegalArgumentException("Invalid operation: " + operation);
            }
        }

        for (int i = 0; i < operations.size(); i++) {
            if ("DIVIDE".equalsIgnoreCase(operations.get(i)) && operands.get(i).doubleValue() == 0) {
                logger.error("Division by zero detected in the operands list.");
                throw new IllegalArgumentException("Division by zero is not allowed.");
            }
        }

        logger.info("Chain calculation request is valid.");
    }

}
