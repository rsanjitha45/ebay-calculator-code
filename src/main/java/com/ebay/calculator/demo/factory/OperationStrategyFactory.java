package com.ebay.calculator.demo.factory;

import com.ebay.calculator.demo.enums.Operation;
import com.ebay.calculator.demo.strategy.OperationStrategy;
import com.ebay.calculator.demo.operations.Addition;
import com.ebay.calculator.demo.operations.Division;
import com.ebay.calculator.demo.operations.Multiplication;
import com.ebay.calculator.demo.operations.Subtraction;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Factory to provide the correct {@code OperationStrategy} based on the requested {@code Operation}.
 * New operations can be added without modifying existing code, adhering to the Open-Closed Principle.
 */
@Component
public class OperationStrategyFactory {

    private final Map<Operation, OperationStrategy> opStrategies = new EnumMap<>(Operation.class);

    public OperationStrategyFactory() {
        registerOperation(Operation.ADD, (OperationStrategy) new Addition());
        registerOperation(Operation.SUBTRACT, (OperationStrategy) new Subtraction());
        registerOperation(Operation.MULTIPLY, (OperationStrategy) new Multiplication());
        registerOperation(Operation.DIVIDE, (OperationStrategy) new Division());
    }

    /**
     * Registers a custom operation strategy for a specific {@code Operation}.
     * This allows for extending the calculator with new operations without modifying the existing code.
     *
     * @param op       the {@code Operation} enum constant representing the operation to be registered
     * @param strategy the {@code OperationStrategy} implementation that defines the operation's behavior
     * @throws NullPointerException if the provided operation or strategy is {@code null}
     */
    public void registerOperation(final Operation op, final OperationStrategy strategy) {
        if (Objects.isNull(op) || Objects.isNull(strategy)) {
            throw new IllegalArgumentException("Operation or strategy cannot be null.");
        }
        opStrategies.put(op, strategy);
    }

    /**
     * Returns the appropriate {@code OperationStrategy} for the given {@code Operation}.
     *
     * @param operation the operation to get the strategy for
     * @return the corresponding {@code OperationStrategy}
     * @throws UnsupportedOperationException if the operation is not registered
     */
    public OperationStrategy getStrategy(Operation operation) {
        if (opStrategies.containsKey(operation)) {
            return opStrategies.get(operation);
        } else {
            throw new UnsupportedOperationException("No strategy registered for operation: " + operation);
        }
    }

    /**
     * Registers a custom operation strategy for a specific {@code Operation}.
     *
     * @param operation the operation to register
     * @param strategy the corresponding strategy to associate with the operation
     */
    public void registerCustomOperation(Operation operation, OperationStrategy strategy) {
        opStrategies.put(operation, strategy);
    }
}
