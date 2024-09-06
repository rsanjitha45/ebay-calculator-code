package com.ebay.calculator.demo.strategy;

import java.util.function.BiFunction;


/**
 * A functional interface representing a strategy for performing an arithmetic operation on two {@code Double} values.
 *
 * <p>This interface extends the {@code BiFunction} interface, which takes two {@code Double} arguments and produces
 * a {@code Double} result. It can be implemented to define custom behavior for arithmetic operations in the {@link Calculator} class.</p>
 *
 * <p>As a functional interface, {@code OperationStrategy} can be used with lambda expressions or method references.</p>
 *
 * @see BiFunction
 * @see Calculator
 */
@FunctionalInterface
public interface OperationStrategy extends BiFunction<Number, Number, Number> {}
