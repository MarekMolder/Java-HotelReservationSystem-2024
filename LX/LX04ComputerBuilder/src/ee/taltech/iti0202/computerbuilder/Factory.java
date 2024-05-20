package ee.taltech.iti0202.computerbuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Factory {
    public static Computer assembleComputer(Optional<BigDecimal> optionalBudget, Optional<EUseCase> optionalUseCase, EComputerType type, Store store) {
        BigDecimal budget = optionalBudget.orElse(BigDecimal.valueOf(Double.MAX_VALUE)); // Set to max value if not provided
        EUseCase useCase = optionalUseCase.orElse(null); // Use null if not provided

        List<Component> cpus = new ArrayList<>();
        List<Component> gpus = new ArrayList<>();
        List<Component> rams = new ArrayList<>();
        List<Component> motherboards = new ArrayList<>();
        List<Component> storages = new ArrayList<>();
        List<Component> psus = new ArrayList<>();
        List<Component> cases = new ArrayList<>();
        List<Component> keyboards = new ArrayList<>();
        List<Component> touchpads = new ArrayList<>();
        List<Component> screens = new ArrayList<>();
        List<Component> batteries = new ArrayList<>();

        for (Component component : store.getAvailableComponents()) {
            switch (component.getType()) {
                case CPU -> cpus.add(component);
                case GPU -> gpus.add(component);
                case RAM -> rams.add(component);
                case MOTHERBOARD -> motherboards.add(component);
                case HDD, SSD -> storages.add(component);
                case PSU -> psus.add(component);
                case CASE -> cases.add(component);
                case KEYBOARD -> keyboards.add(component);
                case TOUCHPAD -> touchpads.add(component);
                case SCREEN -> screens.add(component);
                case BATTERY -> batteries.add(component);
            }
        }

        // Sort components by performance points in descending order
        cpus.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        gpus.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        rams.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        motherboards.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        storages.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        psus.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        cases.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());

        // For laptops only
        keyboards.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        touchpads.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        screens.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        batteries.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());

        Component selectedCPU = cpus.get(0);
        Component selectedGPU = gpus.get(0);
        Component selectedRAM = rams.get(0);
        Component selectedMotherboard = motherboards.get(0);
        Component selectedStorage = storages.get(0);
        Component selectedPSU = psus.get(0);
        Component selectedCase = cases.get(0);

        // Initialize additional components for Laptop
        Component selectedKeyboard = null;
        Component selectedTouchpad = null;
        Component selectedScreen = null;
        Component selectedBattery = null;

        if (type == EComputerType.LAPTOP) {
            selectedKeyboard = keyboards.get(0);
            selectedTouchpad = touchpads.get(0);
            selectedScreen = screens.get(0);
            selectedBattery = batteries.get(0);
        }

        // Calculate initial cost
        BigDecimal totalCost = selectedCPU.getPrice()
                .add(selectedGPU.getPrice())
                .add(selectedRAM.getPrice())
                .add(selectedMotherboard.getPrice())
                .add(selectedStorage.getPrice())
                .add(selectedPSU.getPrice())
                .add(selectedCase.getPrice());

        if (type == EComputerType.LAPTOP) {
            totalCost = totalCost.add(selectedKeyboard.getPrice())
                    .add(selectedTouchpad.getPrice())
                    .add(selectedScreen.getPrice())
                    .add(selectedBattery.getPrice());
        }

        // Create list of components in priority order based on use case
        List<Component> priorityList = new ArrayList<>();
        if (useCase != null) {
            if (useCase == EUseCase.GAMING) {
                priorityList.add(selectedGPU);
                priorityList.add(selectedCPU);
            } else if (useCase == EUseCase.WORKSTATION) {
                priorityList.add(selectedCPU);
                priorityList.add(selectedGPU);
            }
        }

        // Add the rest of the components in the default order
        priorityList.add(selectedRAM);
        priorityList.add(selectedMotherboard);
        priorityList.add(selectedStorage);
        priorityList.add(selectedPSU);
        priorityList.add(selectedCase);

        if (type == EComputerType.LAPTOP) {
            priorityList.add(selectedKeyboard);
            priorityList.add(selectedTouchpad);
            priorityList.add(selectedScreen);
            priorityList.add(selectedBattery);
        }

        // Calculate total power consumption
        int totalPowerConsumption = calculateTotalPowerConsumption(priorityList);

        // Downgrade components until the total cost is within the budget and power consumption is within PSU capacity
        while (totalCost.compareTo(budget) > 0 || totalPowerConsumption > selectedPSU.getPowerConsumption()) {
            boolean downgraded = false;

            for (Component component : priorityList) {
                List<Component> componentList = getComponentListByType(component.getType(), cpus, gpus, rams, motherboards, storages, psus, cases, keyboards, touchpads, screens, batteries);

                int index = componentList.indexOf(component);
                if (index + 1 < componentList.size()) {
                    Component nextBestComponent = componentList.get(index + 1);
                    totalCost = totalCost.subtract(component.getPrice()).add(nextBestComponent.getPrice());
                    totalPowerConsumption = totalPowerConsumption - component.getPowerConsumption() + nextBestComponent.getPowerConsumption();
                    priorityList.set(priorityList.indexOf(component), nextBestComponent);
                    downgraded = true;
                    break;
                }
            }

            if (!downgraded) {
                throw new IllegalArgumentException("Cannot assemble a computer within the given budget and power constraints");
            }
        }

        // Create and return the appropriate Computer object
        if (type == EComputerType.PC) {
            return new Pc(priorityList.get(0), priorityList.get(1), priorityList.get(2), priorityList.get(3), priorityList.get(4), priorityList.get(5), priorityList.get(6));
        } else {
            return new Laptop(priorityList.get(0), priorityList.get(1), priorityList.get(2), priorityList.get(3), priorityList.get(4), priorityList.get(5), priorityList.get(6),
                    priorityList.get(7), priorityList.get(8), priorityList.get(9), priorityList.get(10));
        }
    }

    private static int calculateTotalPowerConsumption(List<Component> components) {
        return components.stream().mapToInt(Component::getPowerConsumption).sum();
    }

    private static List<Component> getComponentListByType(Component.Type type, List<Component> cpus, List<Component> gpus, List<Component> rams,
                                                          List<Component> motherboards, List<Component> storages, List<Component> psus, List<Component> cases,
                                                          List<Component> keyboards, List<Component> touchpads, List<Component> screens, List<Component> batteries) {
        return switch (type) {
            case CPU -> cpus;
            case GPU -> gpus;
            case RAM -> rams;
            case MOTHERBOARD -> motherboards;
            case HDD, SSD -> storages;
            case PSU -> psus;
            case CASE -> cases;
            case KEYBOARD -> keyboards;
            case TOUCHPAD -> touchpads;
            case SCREEN -> screens;
            case BATTERY -> batteries;
        };
    }
}

