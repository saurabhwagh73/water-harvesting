package com.waterharvest.service;

import com.waterharvest.model.Models.*;
import com.waterharvest.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContentService {

    private final BenefitRepository benefits;
    private final ComponentRepository components;
    private final HistoryEventRepository history;
    private final RegionRepository regions;
    private final FaqRepository faqs;

    public ContentService(BenefitRepository benefits, ComponentRepository components,
                          HistoryEventRepository history, RegionRepository regions, FaqRepository faqs) {
        this.benefits = benefits; this.components = components; this.history = history;
        this.regions = regions; this.faqs = faqs;
    }

    public Overview overview() {
        return new Overview(
                "Rainwater Harvesting",
                "Catch every drop. Store it. Reuse it. Recharge the earth.",
                "Rainwater harvesting (RWH) is the collection and storage of rain, rather than letting it run off. "
                        + "Water is gathered from a roof-like surface and directed to a tank, cistern, well, shaft, "
                        + "borehole or reservoir, so it can be reused or seep down to restore groundwater.",
                List.of("Garden watering", "Livestock", "Irrigation", "Toilet flushing & laundry",
                        "Car washing", "Domestic use (with proper treatment)", "Groundwater recharge"),
                List.of(new Stat("1 mm = 1 L", "of water per m² of roof"),
                        new Stat("1.3 °C", "cooler buildings with green roofs + catchment"),
                        new Stat("20-30 %", "typical mains-water saving in UK homes"),
                        new Stat("~40 %", "of Thailand's rural population relies on RWH")));
    }

    public List<Benefit> benefits() {
        return benefits.findAllByOrderByIdAsc().stream()
                .map(e -> new Benefit(e.getIcon(), e.getTitle(), e.getDescription())).toList();
    }

    public List<Component> components() {
        return components.findAllByOrderByStepAsc().stream()
                .map(e -> new Component(e.getStep(), e.getName(), e.getDescription())).toList();
    }

    public List<HistoryEvent> history() {
        return history.findAllByOrderBySortOrderAsc().stream()
                .map(e -> new HistoryEvent(e.getPeriodLabel(), e.getPlace(), e.getDescription())).toList();
    }

    public List<Region> regions() {
        return regions.findAllByOrderByIdAsc().stream()
                .map(e -> new Region(e.getName(), e.getFlag(), e.getHighlightText())).toList();
    }

    public List<Faq> faqs() {
        return faqs.findAllByOrderByIdAsc().stream()
                .map(e -> new Faq(e.getQuestion(), e.getAnswer())).toList();
    }
}