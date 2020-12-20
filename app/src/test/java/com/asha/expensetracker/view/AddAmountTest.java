package com.asha.expensetracker.view;

import org.junit.Test;

import static com.asha.expensetracker.view.AddAmount.checkAmountNegative;
import static org.junit.Assert.*;

public class AddAmountTest {

    @Test
    public void checkAmountNegativeTrue() {
        boolean result = checkAmountNegative(500);

        assertEquals(true, result);
    }
}