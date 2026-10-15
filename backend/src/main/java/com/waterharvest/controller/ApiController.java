package com.waterharvest.controller;

import com.waterharvest.model.Models.*;
import com.waterharvest.service.CalculatorService;
import com.waterharvest.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ContentService content;
    private final CalculatorService calculator;

    public ApiController(ContentService content, CalculatorService calculator) {
        this.content = content;
        this.calculator = calculator;
    }

    @GetMapping("/overview")   public Overview overview()          { return content.overview(); }
    @GetMapping("/benefits")   public List<Benefit> benefits()     { return content.benefits(); }
    @GetMapping("/components") public List<Component> components() { return content.components(); }
    @GetMapping("/history")    public List<HistoryEvent> history() { return content.history(); }
    @GetMapping("/regions")    public List<Region> regions()       { return content.regions(); }
    @GetMapping("/faqs")       public List<Faq> faqs()             { return content.faqs(); }

    @PostMapping("/calculate")
    public CalcResult calculate(@Valid @RequestBody CalcRequest request) {
        return calculator.calculate(request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getDefaultMessage()).findFirst().orElse("Invalid input");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", msg));
    }
}
