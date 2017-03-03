package com.nopointer.server;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ovcharuk on 03.03.17.
 */
public class CalculatorTest {
    @Test
    public void sum() throws Exception {
        Calculator calc = new Calculator();
        assertEquals(calc.sum(10, 12), 22);
    }

}