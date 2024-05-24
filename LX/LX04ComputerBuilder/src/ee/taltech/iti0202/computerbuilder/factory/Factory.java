package ee.taltech.iti0202.computerbuilder.factory;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.Laptop;
import ee.taltech.iti0202.computerbuilder.computer.Pc;
import ee.taltech.iti0202.computerbuilder.store.EComputerType;
import ee.taltech.iti0202.computerbuilder.store.EUseCase;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        // Set to max value if not provided
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
        List<Component> cpus = filterAndSortComponents(components, Component.Type.CPU);
        List<Component> gpus = filterAndSortComponents(components, Component.Type.GPU);
        List<Component> rams = filterAndSortComponents(components, Component.Type.RAM);
        List<Component> motherboards = filterAndSortComponents(components, Component.Type.MOTHERBOARD);
        List<Component> storages = filterAndSortComponents(components, Component.Type.HDD, Component.Type.SSD);
        List<Component> psus = filterAndSortComponents(components, Component.Type.PSU);
        List<Component> cases = filterAndSortComponents(components, Component.Type.CASE);

        List<Component> selectedComponents = new ArrayList<>();

        if (type == EComputerType.LAPTOP) {
            List<Component> keyboards = filterAndSortComponents(components, Component.Type.KEYBOARD);
            List<Component> touchpads = filterAndSortComponents(components, Component.Type.TOUCHPAD);
            List<Component> screens = filterAndSortComponents(components, Component.Type.SCREEN);
            List<Component> batteries = filterAndSortComponents(components, Component.Type.BATTERY);

            selectedComponents.add(keyboards.get(0));
            selectedComponents.add(touchpads.get(0));
            selectedComponents.add(screens.get(0));
            selectedComponents.add(batteries.get(0));
            selectedComponents.add(cases.get(0));
            selectedComponents.add(storages.get(0));
            selectedComponents.add(motherboards.get(0));
            selectedComponents.add(rams.get(0));
            selectedComponents.add(gpus.get(0));
            selectedComponents.add(cpus.get(0));
            selectedComponents.add(psus.get(0));

        } else if (type == EComputerType.PC) {
            if (optionalUseCase == EUseCase.GAMING) {
                selectedComponents.add(cases.get(0));
                selectedComponents.add(storages.get(0));
                selectedComponents.add(motherboards.get(0));
                selectedComponents.add(rams.get(0));
                selectedComponents.add(cpus.get(0));
                selectedComponents.add(gpus.get(0));
                selectedComponents.add(psus.get(0));
            } else if (optionalUseCase == EUseCase.WORKSTATION || optionalUseCase == null) {
                selectedComponents.add(cases.get(0));
                selectedComponents.add(storages.get(0));
                selectedComponents.add(motherboards.get(0));
                selectedComponents.add(rams.get(0));
                selectedComponents.add(gpus.get(0));
                selectedComponents.add(cpus.get(0));
                selectedComponents.add(psus.get(0));
            }
        }
        return selectedComponents;
    }

    /**
     * Filters and sorts components based on type.
     *
     * @param components the list of available components
     * @param types the types of components to filter
     * @return the list of filtered and sorted components
     */
    private static List<Component> filterAndSortComponents(List<Component> components, Component.Type... types) {
        List<Component> filteredComponents = new ArrayList<>();
        for (Component component : components) {
            for (Component.Type type : types) {
                if (component.getType() == type) {
                    filteredComponents.add(component);
                    break;
                }
            }
        }
        filteredComponents.sort((c1, c2) -> c2.getPerformancePoints() - c1.getPerformancePoints());
        return filteredComponents;
    }

    /**
     * Calculates the total cost of the selected components.
     *
     * @param components the list of selected components
     * @param profitMargin the profit margin of the store
     * @return the total cost
     */
    private static BigDecimal calculateTotalCost(List<Component> components, BigDecimal profitMargin) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Component component : components) {
            totalCost = totalCost.add(component.getPrice());
        }
        return totalCost.multiply(profitMargin);
    }

    /**
     * Calculates the total power consumption of the selected components.
     *
     * @param components the list of selected components
     * @return the total power consumption
     */
    private static int calculateTotalPowerConsumption(List<Component> components) {
        int totalPowerConsumption = 0;
        for (Component component : components) {
            if (component.getType() == Component.Type.PSU || component.getType() == Component.Type.BATTERY) {
                continue;
            } else {
                totalPowerConsumption += component.getPowerConsumption();
            }
        }
        return totalPowerConsumption;
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
        Component psu = null;
        for (Component component : selectedComponents) {
            if (component.getType() == Component.Type.PSU) {
                psu = component;
            }
        }

        List<Component> cpus = filterAndSortComponents(availableComponents, Component.Type.CPU);
        List<Component> gpus = filterAndSortComponents(availableComponents, Component.Type.GPU);
        List<Component> rams = filterAndSortComponents(availableComponents, Component.Type.RAM);
        List<Component> motherboards = filterAndSortComponents(availableComponents, Component.Type.MOTHERBOARD);
        List<Component> storages = filterAndSortComponents(availableComponents, Component.Type.HDD, Component.Type.SSD);
        List<Component> psus = filterAndSortComponents(availableComponents, Component.Type.PSU);
        List<Component> cases = filterAndSortComponents(availableComponents, Component.Type.CASE);
        List<Component> keyboards = filterAndSortComponents(availableComponents, Component.Type.KEYBOARD);
        List<Component> touchpads = filterAndSortComponents(availableComponents, Component.Type.TOUCHPAD);
        List<Component> screens = filterAndSortComponents(availableComponents, Component.Type.SCREEN);
        List<Component> batteries = filterAndSortComponents(availableComponents, Component.Type.BATTERY);

        while (totalCost.compareTo(budget) > 0 || totalPowerConsumption > Objects.requireNonNull(psu)
                .getPowerConsumption()) {
            boolean downgraded = false;

            for (int i = 0; i < selectedComponents.size(); i++) {
                Component component = selectedComponents.get(i);
                List<Component> componentList = getComponentListByType(component.getType(),
                        cpus, gpus, rams, motherboards, storages,
                        psus, cases, keyboards, touchpads, screens, batteries);

                int index = componentList.indexOf(component);
                if (index + 1 < componentList.size()) {
                    Component nextBestComponent = componentList.get(index + 1);
                    totalCost = totalCost.subtract(component.getPrice()).add(nextBestComponent.getPrice());
                    totalPowerConsumption = totalPowerConsumption - component.getPowerConsumption()
                            + nextBestComponent.getPowerConsumption();
                    selectedComponents.set(i, nextBestComponent);

                    if (totalCost.compareTo(budget) <= 0 && totalPowerConsumption
                            <= selectedComponents.get(5).getPowerConsumption()) {
                        return;
                    }

                    downgraded = true;
                    break;
                }
            }

            if (!downgraded) {
                throw new IllegalArgumentException("Cannot assemble a computer"
                        + " within the given budget and power constraints");
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
            if (useCase == EUseCase.GAMING) {
                return new Pc(components.get(4), components.get(5), components.get(3),
                        components.get(2), components.get(1), components.get(6), components.get(0));
            } else {
                return new Pc(components.get(5), components.get(4), components.get(3),
                        components.get(2), components.get(1), components.get(6), components.get(0));
            }
        } else {
            return new Laptop(components.get(9), components.get(8), components.get(7),
                    components.get(6), components.get(5), components.get(10), components.get(4),
                    components.get(0), components.get(1), components.get(2), components.get(3));
        }
    }

    /**
     * Retrieves a list of components based on the specified component type.
     *
     * @param type        The type of component to retrieve.
     * @param cpus        List of available CPUs.
     * @param gpus        List of available GPUs.
     * @param rams        List of available RAMs.
     * @param motherboards List of available motherboards.
     * @param storages    List of available storages (HDDs or SSDs).
     * @param psus        List of available PSUs.
     * @param cases       List of available computer cases.
     * @param keyboards   List of available keyboards.
     * @param touchpads   List of available touchpads.
     * @param screens     List of available screens.
     * @param batteries   List of available batteries.
     * @return A list of components based on the specified type.
     */
    private static List<Component> getComponentListByType(Component.Type type, List<Component> cpus,
                                                          List<Component> gpus, List<Component> rams,
                                                          List<Component> motherboards, List<Component> storages,
                                                          List<Component> psus, List<Component> cases,
                                                          List<Component> keyboards, List<Component> touchpads,
                                                          List<Component> screens, List<Component> batteries) {
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
