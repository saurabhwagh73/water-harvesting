package com.waterharvest.service;

import com.waterharvest.model.Models.CalcRequest;
import com.waterharvest.model.Models.CalcResult;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    /** Yield (L) = roof area (m²) x rainfall (mm) x runoff coefficient, since 1 mm on 1 m² = 1 litre. */
    public CalcResult calculate(CalcRequest r) {
        double yield = r.roofAreaM2() * r.annualRainfallMm() * r.runoffCoefficient();
        double annualDemand = r.dailyDemandLitres() * 365;
        double coverage = Math.min(100.0, yield / annualDemand * 100.0);
        double tank = r.dailyDemandLitres() * r.dryDays();

        String message = coverage >= 100
                ? "Your roof can cover your full annual demand. Size the tank for the dry season."
                : "Your roof can cover about %.0f%% of annual demand; use it to supplement other sources."
                        .formatted(coverage);

        return new CalcResult(round(yield), round(annualDemand), round(coverage), round(tank), message);
    }

    private double round(double v) {
        return Math.round(v * 10.0) / 10.0;
    }
}
