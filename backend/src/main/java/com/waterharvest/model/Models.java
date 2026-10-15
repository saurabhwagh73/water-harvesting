package com.waterharvest.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

import java.util.List;


public final class Models {
    private Models() {}

    public record Overview(String title, String tagline, String definition, List<String> uses, List<Stat> stats) {}
    public record Stat(String value, String label) {}
    public record Benefit(String icon, String title, String description) {}
    public record Component(int step, String name, String description) {}
    public record HistoryEvent(String period, String place, String description) {}
    public record Region(String name, String flag, String highlight) {}
    public record Faq(String question, String answer) {}

    public record CalcRequest(
            @Positive(message = "Roof area must be greater than 0") double roofAreaM2,
            @Positive(message = "Annual rainfall must be greater than 0") double annualRainfallMm,
            @DecimalMin(value = "0.1", message = "Runoff coefficient must be 0.1 - 1.0")
            @DecimalMax(value = "1.0", message = "Runoff coefficient must be 0.1 - 1.0") double runoffCoefficient,
            @Positive(message = "Daily demand must be greater than 0") double dailyDemandLitres,
            @Positive(message = "Dry days must be greater than 0") int dryDays) {}

    public record CalcResult(double annualYieldLitres, double annualDemandLitres, double coveragePercent,
                             double suggestedTankLitres, String message) {}
}
