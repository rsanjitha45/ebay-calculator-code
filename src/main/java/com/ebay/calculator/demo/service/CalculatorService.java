package com.ebay.calculator.demo.service;

import com.ebay.calculator.demo.enums.Operation;
import com.ebay.calculator.demo.factory.OperationStrategyFactory;
import com.ebay.calculator.demo.utils.CalculatorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for performing calculations using different arithmetic operations.
 */
@Service
public class CalculatorService {

    private final OperationStrategyFactory strategyFactory;
    private final CalculatorValidator validator;

    @Autowired
    public CalculatorService(OperationStrategyFactory strategyFactory, CalculatorValidator validator) {
        this.strategyFactory = strategyFactory;
        this.validator = validator;
    }

    /**
     * Performs the specified operation on the given operands.
     *
     * @param operation the operation to perform
     * @param num1 the first operand
     * @param num2 the second operand
     * @return the result of the operation
     * @throws UnsupportedOperationException if the operation is not supported
     */
    public Number calculate(Operation operation, Number num1, Number num2) {
        validator.validateOperands(num1, num2);

        if (operation == Operation.DIVIDE) {
            validator.validateDivision(num1, num2);
        }

        return strategyFactory.getStrategy(operation).apply(num1, num2);
    }

    /**
     * Chains multiple operations on an initial value sequentially.
     *
     * @param initialValue the initial value to start the chain
     * @param operations an array of operations to perform
     * @param operands an array of operands corresponding to each operation
     * @return the final result after applying all operations
     * @throws IllegalArgumentException if the lengths of operations and operands do not match
     */
    public Number chainOperations(Number initialValue, List<Operation> operations, List<Number> operands) {
        if (operations.size() != operands.size()) {
            throw new IllegalArgumentException("Operations and operands lists must have the same length.");
        }

        Number result = initialValue;
        for (int i = 0; i < operations.size(); i++) {
            result = calculate(operations.get(i), result, operands.get(i));
        }

        return result;
    }
}
