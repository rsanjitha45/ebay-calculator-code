import com.ebay.calculator.demo.enums.Operation;
import com.ebay.calculator.demo.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private CalculatorService calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAddition() {
        assertEquals(5, calculator.calculate(Operation.ADD, 2, 3));
        assertEquals(0, calculator.calculate(Operation.ADD, 2, -2));
        assertEquals(-5, calculator.calculate(Operation.ADD, -2, -3));
    }

    @Test
    public void testSubtraction() {
        assertEquals(1, calculator.calculate(Operation.SUBTRACT, 5, 4));
        assertEquals(9, calculator.calculate(Operation.SUBTRACT, 5, -4));
        assertEquals(-9, calculator.calculate(Operation.SUBTRACT, -5, 4));
        assertEquals(0, calculator.calculate(Operation.SUBTRACT, 5, 5));
        assertEquals(-10, calculator.calculate(Operation.SUBTRACT, -5, 5));
        assertEquals(0, calculator.calculate(Operation.SUBTRACT, 0, 0));
    }

    @Test
    public void testMultiplication() {
        assertEquals(6, calculator.calculate(Operation.MULTIPLY, 2, 3));
        assertEquals(-6, calculator.calculate(Operation.MULTIPLY, -2, 3));
        assertEquals(0, calculator.calculate(Operation.MULTIPLY, -2, 0));
        assertEquals(0, calculator.calculate(Operation.MULTIPLY, 0, 3));
        assertEquals(9, calculator.calculate(Operation.MULTIPLY, -3, -3));
    }

    @Test
    public void testDivision() {
        assertEquals(2, calculator.calculate(Operation.DIVIDE, 6, 3));
        assertEquals(-2, calculator.calculate(Operation.DIVIDE, -6, 3));
        assertEquals(0, calculator.calculate(Operation.DIVIDE, 0, 3));
        assertThrows(ArithmeticException.class, () -> calculator.calculate(Operation.DIVIDE, 1, 0));
    }

    @Test
    public void testChainingOperationsWithLists() {
        // Test chaining with no operations
        assertEquals(1.0, calculator.chainOperations(1, List.of(), List.of()));

        // Test simple arithmetic chain: 5 + 3 = 8, 8 * 2 = 16
        assertEquals(16.0, calculator.chainOperations(5,
                Arrays.asList(Operation.ADD, Operation.MULTIPLY),
                Arrays.asList(3, 2)));

        // Test more complex chain: 0 + 1 = 1, 1 - 2 = -1, -1 * 3 = -3, -3 / 4 = -0.75
        assertEquals(-0.75, calculator.chainOperations(0,
                Arrays.asList(Operation.ADD, Operation.SUBTRACT, Operation.MULTIPLY, Operation.DIVIDE),
                Arrays.asList(1.0, 2.0, 3.0, 4.0)));

        // Test with zero division to confirm exception handling
        assertThrows(ArithmeticException.class, () -> calculator.chainOperations(1,
                Arrays.asList(Operation.ADD, Operation.DIVIDE),
                Arrays.asList(3.2, 0.0)));

        // Test with null values to confirm IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> calculator.chainOperations(null,
                Arrays.asList(Operation.ADD, Operation.SUBTRACT),
                Arrays.asList(2.0, 3.0)));
    }

    @Test
    public void testIllegalArgumentWhenListsAreMismatched() {
        assertThrows(IllegalArgumentException.class, () -> calculator.chainOperations(1,
                Arrays.asList(Operation.ADD, Operation.SUBTRACT),  // Two operations
                Arrays.asList(1)));  // One operand only

        assertThrows(IllegalArgumentException.class, () -> calculator.chainOperations(0,
                null,  // null list of operations
                Arrays.asList(1, 2)));
        assertThrows(IllegalArgumentException.class, () -> calculator.chainOperations(0,
                Arrays.asList(Operation.ADD),  // valid operations list
                null));  // null list of operands
    }
}

    @Test
    public void testUnsupportedOperation() {
        assertThrows(UnsupportedOperationException.class, () -> calculator.calculate(null, 1, 1));
    }

    @Test
    public void testIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(Operation.ADD, 1, null));
    }

}
