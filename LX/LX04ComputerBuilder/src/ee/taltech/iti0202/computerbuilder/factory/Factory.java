package ee.taltech.iti0202.computerbuilder.factory;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.Laptop;
import ee.taltech.iti0202.computerbuilder.computer.Pc;
import ee.taltech.iti0202.computerbuilder.store.EComputerType;
import ee.taltech.iti0202.computerbuilder.store.EUseCase;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Factory {

    /**
     * Assembles a computer based on optional budget, optional use case, and required type.
     *
     * @param optionalBudget the optional budget for the computer
     * @param optionalUseCase the optional use case for the computer
     * @param type the type of the computer (e.g., LAPTOP, PC)
     * @param store the store to get components from
     * @return the assembled computer
     */
    public static Computer assembleComputer(Optional<BigDecimal> optionalBudget,
                                            Optional<EUseCase> optionalUseCase, EComputerType type, Store store) {
        BigDecimal budget = optionalBudget.orElse(BigDecimal.valueOf(Double.MAX_VALUE));
        EUseCase useCase = optionalUseCase.orElse(null); // Use null if not provided

        List<Component> components = store.getAvailableComponents();
        List<Component> selectedComponents = selectBestComponents(components, type, useCase);

        BigDecimal totalCost = calculateTotalCost(selectedComponents, store.getProfitMargin());
        int totalPowerConsumption = calculateTotalPowerConsumption(selectedComponents);

        adjustComponentsWithinBudgetAndPower(selectedComponents, totalCost, totalPowerConsumption, budget, components);

        return createComputer(type, selectedComponents, useCase);
    }

    /**
     * Selects the best components for the computer based on type and optional use case.
     *
     * @param components the list of available components
     * @param type the type of the computer
     * @param optionalUseCase the optional use case for the computer
     * @return the list of selected components
     */
    private static List<Component> selectBestComponents(List<Component> components,
                                                        EComputerType type, EUseCase optionalUseCase) {
        Map<Component.Type, List<Component>> componentsByType = components.stream()
                .collect(Collectors.groupingBy(Component::getType));

        List<Component> selectedComponents = new ArrayList<>();

        if (type == EComputerType.LAPTOP) {
            selectedComponents.addAll(selectLaptopComponents(componentsByType));
        } else if (type == EComputerType.PC) {
            selectedComponents.addAll(selectPcComponents(componentsByType, optionalUseCase));
        }

        return selectedComponents;
    }

    private static List<Component> selectLaptopComponents(Map<Component.Type, List<Component>> componentsByType) {
        return Arrays.asList(
                getBestComponent(componentsByType, Component.Type.KEYBOARD),
                getBestComponent(componentsByType, Component.Type.TOUCHPAD),
                getBestComponent(componentsByType, Component.Type.SCREEN),
                getBestComponent(componentsByType, Component.Type.BATTERY),
                getBestComponent(componentsByType, Component.Type.CASE),
                getBestComponent(componentsByType, Component.Type.HDD, Component.Type.SSD),
                getBestComponent(componentsByType, Component.Type.MOTHERBOARD),
                getBestComponent(componentsByType, Component.Type.RAM),
                getBestComponent(componentsByType, Component.Type.GPU),
                getBestComponent(componentsByType, Component.Type.CPU),
                getBestComponent(componentsByType, Component.Type.PSU)
        );
    }

    private static List<Component> selectPcComponents(Map<Component.Type, List<Component>> componentsByType, EUseCase useCase) {
        List<Component> selectedComponents;

        if (useCase == EUseCase.GAMING) {
            selectedComponents = Arrays.asList(
                    getBestComponent(componentsByType, Component.Type.CASE),
                    getBestComponent(componentsByType, Component.Type.HDD, Component.Type.SSD),
                    getBestComponent(componentsByType, Component.Type.MOTHERBOARD),
                    getBestComponent(componentsByType, Component.Type.RAM),
                    getBestComponent(componentsByType, Component.Type.CPU),
                    getBestComponent(componentsByType, Component.Type.GPU),
                    getBestComponent(componentsByType, Component.Type.PSU)
            );
        } else if (useCase == EUseCase.WORKSTATION || useCase == null) {
            selectedComponents = Arrays.asList(
                    getBestComponent(componentsByType, Component.Type.CASE),
                    getBestComponent(componentsByType, Component.Type.HDD, Component.Type.SSD),
                    getBestComponent(componentsByType, Component.Type.MOTHERBOARD),
                    getBestComponent(componentsByType, Component.Type.RAM),
                    getBestComponent(componentsByType, Component.Type.GPU),
                    getBestComponent(componentsByType, Component.Type.CPU),
                    getBestComponent(componentsByType, Component.Type.PSU)
            );
        } else {
            throw new IllegalArgumentException("Invalid use case");
        }

        return selectedComponents;
    }

    private static Component getBestComponent(Map<Component.Type, List<Component>> componentsByType, Component.Type... types) {
        return Arrays.stream(types)
                .map(componentsByType::get)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .max(Comparator.comparingInt(Component::getPerformancePoints))
                .orElseThrow(() -> new IllegalArgumentException("No components available for types: " + Arrays.toString(types)));
    }

    /**
     * Calculates the total cost of the selected components.
     *
     * @param components the list of selected components
     * @param profitMargin the profit margin of the store
     * @return the total cost
     */
    private static BigDecimal calculateTotalCost(List<Component> components, BigDecimal profitMargin) {
        BigDecimal totalCost = components.stream()
                .map(Component::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalCost.multiply(profitMargin);
    }

    /**
     * Calculates the total power consumption of the selected components.
     *
     * @param components the list of selected components
     * @return the total power consumption
     */
    private static int calculateTotalPowerConsumption(List<Component> components) {
        return components.stream()
                .filter(component -> component.getType() != Component.Type.PSU && component.getType() != Component.Type.BATTERY)
                .mapToInt(Component::getPowerConsumption)
                .sum();
    }

    /**
     * Adjusts the selected components to fit within the budget and power constraints.
     *
     * @param selectedComponents the list of selected components
     * @param totalCost the total cost of the selected components
     * @param totalPowerConsumption the total power consumption of the selected components
     * @param budget the budget for the computer
     * @param availableComponents the list of available components
     */
    private static void adjustComponentsWithinBudgetAndPower(List<Component> selectedComponents,
                                                             BigDecimal totalCost, int totalPowerConsumption,
                                                             BigDecimal budget, List<Component> availableComponents) {
        Map<Component.Type, List<Component>> componentsByType = availableComponents.stream()
                .collect(Collectors.groupingBy(Component::getType));

        while (totalCost.compareTo(budget) > 0) {
            boolean downgraded = false;

            for (int i = 0; i < selectedComponents.size(); i++) {
                Component component = selectedComponents.get(i);
                List<Component> componentList = componentsByType.get(component.getType());

                int index = componentList.indexOf(component);
                if (index + 1 < componentList.size()) {
                    Component nextBestComponent = componentList.get(index + 1);
                    totalCost = totalCost.subtract(component.getPrice()).add(nextBestComponent.getPrice());
                    totalPowerConsumption = totalPowerConsumption - component.getPowerConsumption()
                            + nextBestComponent.getPowerConsumption();
                    selectedComponents.set(i, nextBestComponent);

                    if (totalCost.compareTo(budget) <= 0 && totalPowerConsumption
                            <= selectedComponents.get(6).getPowerConsumption()) {
                        return;
                    }

                    downgraded = true;
                    break;
                }
            }

            if (!downgraded) {
                throw new IllegalArgumentException("Cannot assemble a computer within the given budget and power constraints");
            }
        }
    }

    /**
     * Creates a computer based on the specified type, components, and use case.
     *
     * @param type       The type of computer (PC or Laptop).
     * @param components The list of components to build the computer.
     * @param useCase    The primary use case for the computer (e.g., gaming, general use).
     * @return A Computer object representing the created computer.
     */
    private static Computer createComputer(EComputerType type, List<Component> components, EUseCase useCase) {
        if (type == EComputerType.PC) {
            Pc.PcBuilder pcBuilder = new Pc.PcBuilder();
            pcBuilder.setCpu(components.get(5));
            pcBuilder.setGpu(components.get(4));
            pcBuilder.setRam(components.get(3));
            pcBuilder.setMotherboard(components.get(2));
            pcBuilder.setStorage(components.get(1));
            pcBuilder.setPsu(components.get(6));
            pcBuilder.setPcCase(components.get(0));
            return pcBuilder.build();
        } else {
            Laptop.LaptopBuilder laptopBuilder = new Laptop.LaptopBuilder();
            laptopBuilder.setCpu(components.get(9));
            laptopBuilder.setGpu(components.get(8));
            laptopBuilder.setRam(components.get(7));
            laptopBuilder.setMotherboard(components.get(6));
            laptopBuilder.setStorage(components.get(5));
            laptopBuilder.setPsu(components.get(10));
            laptopBuilder.setPcCase(components.get(4));
            laptopBuilder.setKeyboard(components.get(0));
            laptopBuilder.setTouchpad(components.get(1));
            laptopBuilder.setScreen(components.get(2));
            laptopBuilder.setBattery(components.get(3));
            return laptopBuilder.build();
        }
    }

}
