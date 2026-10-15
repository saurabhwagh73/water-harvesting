package com.waterharvest.config;

import com.waterharvest.entity.*;
import com.waterharvest.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataSeeder implements CommandLineRunner {

    private final BenefitRepository benefits;
    private final ComponentRepository components;
    private final HistoryEventRepository history;
    private final RegionRepository regions;
    private final FaqRepository faqs;

    public DataSeeder(BenefitRepository benefits, ComponentRepository components,
                      HistoryEventRepository history, RegionRepository regions, FaqRepository faqs) {
        this.benefits = benefits; this.components = components; this.history = history;
        this.regions = regions; this.faqs = faqs;
    }

    @Override
    public void run(String... args) {
        if (benefits.count() == 0) {
            benefits.saveAll(List.of(
                    new BenefitEntity("💧", "Independent water supply", "Keeps water available during restrictions, dry seasons and droughts."),
                    new BenefitEntity("🌍", "Sustains groundwater", "Less demand on wells and borewells, and recharge raises falling water tables."),
                    new BenefitEntity("🌊", "Reduces flooding & runoff", "Captured water no longer overloads drains, sewers and low-lying areas."),
                    new BenefitEntity("🧂", "Naturally low in salinity", "Rainwater is almost free of salts and can dilute salty groundwater."),
                    new BenefitEntity("💰", "Cost-effective", "Simple systems are affordable and can be maintained by local people."),
                    new BenefitEntity("🌱", "Eco-friendly", "No pollution, and less need for large dams that damage ecosystems."),
                    new BenefitEntity("🏙️", "Smarter cities", "Eases pressure on municipal supply and sewer systems; supports urban farming and SDG 6."),
                    new BenefitEntity("🚜", "Boosts farming", "Trapping runoff protects crops from water scarcity and slows soil erosion on hillsides.")));
        }
        if (components.count() == 0) {
            components.saveAll(List.of(
                    new ComponentEntity(1, "Catchment (roof)", "The surface that receives rain. It must be large enough to supply an adequate flow."),
                    new ComponentEntity(2, "Gutters & downpipes", "Channels that carry roof runoff towards the filter and tank."),
                    new ComponentEntity(3, "Pre-filter / vortex filter", "Removes leaves, dust and large sediment before water enters the tank."),
                    new ComponentEntity(4, "First-flush diverter", "Diverts the dirty first runoff to waste, sharply reducing contaminants."),
                    new ComponentEntity(5, "Storage tank / cistern", "Sized to cover daily use through the dry season. Keep it covered against mosquitoes."),
                    new ComponentEntity(6, "Pump", "Needed only for pressurised systems that feed taps, toilets and washing machines."),
                    new ComponentEntity(7, "Treatment (optional)", "UV light, chlorination or post-filtration, plus boiling, make water safe to drink."),
                    new ComponentEntity(8, "Overflow & recharge well", "Excess water flows to a well, pit or borehole and percolates down to the groundwater.")));
        }
        if (history.count() == 0) {
            history.saveAll(List.of(
                    new HistoryEventEntity(1, "Neolithic Age", "The Levant", "Waterproof lime-plaster cisterns were built into house floors."),
                    new HistoryEventEntity(2, "c. 2500 BC", "Khirbet et-Tell", "A rock-cut, stone-lined cistern held nearly 1,700 m³."),
                    new HistoryEventEntity(3, "2600-1100 BC", "Minoan Crete", "Large cisterns at Myrtos-Pyrgos, Archanes and Zakros stored rainwater."),
                    new HistoryEventEntity(4, "c. 300 BC", "Balochistan & Kutch", "Farming communities used rainwater harvesting for agriculture."),
                    new HistoryEventEntity(5, "Roman era", "Pompeii & the Empire", "Rooftop storage and cisterns were widespread alongside aqueducts."),
                    new HistoryEventEntity(6, "1011-1037 AD", "Tamil Nadu, India", "Chola-era Vīrānam tank, 16 km long, stored water for drinking and irrigation."),
                    new HistoryEventEntity(7, "Medieval", "Venice", "Stone-floored, sand-filtered collection wells supplied the city for centuries."),
                    new HistoryEventEntity(8, "2001", "Tamil Nadu, India", "First Indian state to make rainwater harvesting compulsory in every building.")));
        }
        if (regions.count() == 0) {
            regions.saveAll(List.of(
                    new RegionEntity("India", "🇮🇳", "Tamil Nadu made RWH compulsory in 2001. Mumbai requires it for new buildings (over 1,000 m² in 2002, widened to 300 m² in 2007). Rajasthan revives the traditional chauka system."),
                    new RegionEntity("Thailand", "🇹🇭", "About 40% of the rural population relies on rainwater, one of the world's biggest self-supply examples."),
                    new RegionEntity("United Kingdom", "🇬🇧", "Used for gardens, toilets and laundry; typical mains-water savings are 20-30%."),
                    new RegionEntity("United States", "🇺🇸", "Colorado legalised rooftop collection in 2009; Santa Fe mandates it for new dwellings; Texas offers a sales-tax exemption on equipment."),
                    new RegionEntity("Germany", "🇩🇪", "Frankfurt Airport runs the country's largest system, saving about 1 million m³ a year."),
                    new RegionEntity("Kenya", "🇰🇪", "Harvested rainwater supplies toilets, laundry and irrigation under the 2016 Water Act."),
                    new RegionEntity("Sri Lanka", "🇱🇰", "Promoted by a 2007 law and the Lanka Rainwater Harvesting Forum; ancient tank cascades still irrigate the island."),
                    new RegionEntity("Bermuda", "🇧🇲", "Law requires new buildings to include adequate rainwater harvesting; its white stepped roofs are famous.")));
        }
        if (faqs.count() == 0) {
            faqs.saveAll(List.of(
                    new FaqEntity("Is harvested rainwater safe to drink?",
                            "Rain itself is clean, but roofs add dirt, droppings and pollutants. Use a pre-filter and first-flush diverter, then treat it (boiling, UV, chlorination or fine filtration) before drinking."),
                    new FaqEntity("How is it different from stormwater harvesting?",
                            "RWH mainly collects clean-ish roof runoff for storage and reuse, whereas stormwater harvesting captures runoff from wider land surfaces."),
                    new FaqEntity("How big should my tank be?",
                            "Size it to meet daily demand through the longest dry spell, and make sure the roof is big enough to fill it. Try the calculator above."),
                    new FaqEntity("Can it breed mosquitoes?",
                            "Open cisterns can. Seal all openings with fine mesh, add larvae-eating fish, or treat the water."),
                    new FaqEntity("What can I use unfiltered rainwater for?",
                            "Preliminarily filtered water is fine for toilet flushing, laundry and garden irrigation, which saves potable water for drinking.")));
        }
    }
}