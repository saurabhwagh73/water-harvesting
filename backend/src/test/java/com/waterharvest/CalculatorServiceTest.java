package com.waterharvest;

import com.waterharvest.model.Models.CalcRequest;
import com.waterharvest.service.CalculatorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceTest {
    @Test
    void yieldIsAreaTimesRainfallTimesCoefficient() {
        var result = new CalculatorService().calculate(new CalcRequest(100, 800, 0.8, 200, 60));
        assertEquals(64000.0, result.annualYieldLitres());
        assertEquals(12000.0, result.suggestedTankLitres());
    }
}
