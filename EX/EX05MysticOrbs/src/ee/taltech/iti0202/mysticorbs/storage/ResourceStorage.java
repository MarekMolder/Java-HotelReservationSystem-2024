package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {
    Map<String, Integer> resources = new HashMap<>();
    Map<String, Integer> takenOutResources = new HashMap<>();

    public boolean isEmpty() {
        return resources.isEmpty();
    }

    /**
     * Method to add resources.
     * @param resource
     * @param amount
     */
    public void addResource(String resource, int amount) {
        if (amount > 0) {
            if (!resource.isEmpty() && !resource.trim().isEmpty()) {
                if (resources.containsKey(resource.toLowerCase())) {
                    resources.merge(resource.toLowerCase(), amount, Integer::sum);
                } else {
                    resources.put(resource.toLowerCase(), amount);
                }
            }
        }
    }

    /**
     * Method to get resources amount.
     * @param resource
     * @return
     */
    public int getResourceAmount(String resource) {
        if (resources.containsKey(resource.toLowerCase())) {
            return resources.get(resource.toLowerCase());
        }
        return 0;
    }

    /**
     * Method to check resources.
     * @param resource
     * @param amount
     * @return
     */
    public boolean hasEnoughResource(String resource, int amount) {
        if (resources.containsKey(resource.toLowerCase())) {
            if (amount < 1) {
                return false;
            } else if (resources.get(resource.toLowerCase()) >= amount) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to take resources out.
     * @param resource
     * @param amount
     * @return
     */
    public boolean takeResource(String resource, int amount) {
        if (amount > 0) {
            if (resources.get(resource.toLowerCase()) >= amount) {
                if (takenOutResources.containsKey(resource.toLowerCase())) {
                    takenOutResources.merge(resource.toLowerCase(), amount, Integer::sum);
                } else {
                    takenOutResources.put(resource.toLowerCase(), amount);
                }
                int remainingAmount = resources.get(resource.toLowerCase()) - amount;

                if (remainingAmount > 0) {
                    resources.put(resource.toLowerCase(), remainingAmount);
                } else {
                    resources.remove(resource.toLowerCase());
                }
                return true;
            }
        }
        return false;
    }
}
