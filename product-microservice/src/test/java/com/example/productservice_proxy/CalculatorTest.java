package com.example.productservice_proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("Testing 1+2 = 3")
    // TEST WHEN THEN
    // TEST_WHENADDTWOINT_RETURNINT
    void TestAddTwoInteger_ReturnInteger() {
        // Arrange
        Calculator calculator = new Calculator();
        // Act
        int result = calculator.add(1,2);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void sub() {
    }

    @Test
    void mul() {
    }

    @Test
    @DisplayName("Testing 1/0 throws Arithmetic Exception")
    void TestDivideByZero_ThrowException() {
        Calculator calculator = new Calculator();
//        int result = calculator.div(1, 0);
//        assertEquals(0, result);
        assertThrows(ArithmeticException.class, ()->  calculator.div(1, 0));
    }
}