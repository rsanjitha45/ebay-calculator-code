package com.ebay.calculator.demo.controller;

import com.ebay.calculator.demo.enums.Operation;
import com.ebay.calculator.demo.model.ChainCalculationRequest;
import com.ebay.calculator.demo.service.CalculatorService;
import com.ebay.calculator.demo.utils.CalculatorValidator;
import com.ebay.calculator.demo.model.CalculationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calculator")
@Api("Calculator operations APIs")
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    private final CalculatorService calculatorService;
    private final CalculatorValidator calculatorValidator;

    @Autowired
    public CalculatorController(CalculatorService calculatorService, CalculatorValidator calculatorValidator) {
        this.calculatorService = calculatorService;
        this.calculatorValidator = calculatorValidator;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully performed the calculation"),
            @ApiResponse(code = 400, message = "Invalid input or unsupported operation")
    })
    @PostMapping("/calculate")
    public ResponseEntity<Number> calculate(@RequestBody CalculationRequest request) {
        logger.info("CalculatorController: received calculate request={}", request);
        try {
            Operation validOperation = calculatorValidator.validateOperation(request.getOperation());
            Number result = calculatorService.calculate(validOperation, request.getNum1(), request.getNum2());

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid operation: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            logger.error("Error occurred during calculation: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Performs a chain of calculations based on a series of operations and operands.
     *
     * @param request the request object containing the initial value, operations, and operands of class ChainCalculationRequest
     * @return the result of the chained calculation
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully performed chained calculation"),
            @ApiResponse(code = 400, message = "Invalid input provided")})
    @PostMapping("/chainCalculate")
    public ResponseEntity<Number> chainCalculate(@RequestBody ChainCalculationRequest request) {
        logger.info("CalculatorController: received chainCalculate request={}", request);
        try {
            calculatorValidator.validateChainRequest(request);

            List<Operation> operations = request.getOperations().stream()
                    .map(Operation::valueOf)
                    .collect(Collectors.toList());
            Number result = calculatorService.chainOperations(
                    request.getInitialValue(),
                    operations,
                    request.getOperands().stream().collect(Collectors.toList())
            );

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid operation in request: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
