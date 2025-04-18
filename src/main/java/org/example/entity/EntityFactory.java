package org.example.entity;

import org.example.Logger;
import org.example.entity.creature.Creature;

import java.util.*;

public class EntityFactory {

    private final Map<Class<? extends Entity>, Double> staticEntityTypes;
    private final Map<Class<? extends Creature>, Double> creatureTypes;

    public EntityFactory(
            Map<Class<? extends Entity>, Double> staticEntityTypes,
            Map<Class<? extends Creature>, Double> creatureTypes
    ) {
        this.staticEntityTypes = staticEntityTypes;
        this.creatureTypes = creatureTypes;
    }

    public List<Entity> createStaticEntities(int totalCount) {
        return createEntities(staticEntityTypes, totalCount);
    }

    public List<Creature> createCreatures(int totalCount) {
        return createEntities(creatureTypes, totalCount);
    }

    private <T extends Entity> List<T> createEntities(Map<Class<? extends T>, Double> types, int totalCount) {
        List<T> result = new ArrayList<>();
        double totalWeight = types.values().stream().mapToDouble(Double::doubleValue).sum();

        for (Map.Entry<Class<? extends T>, Double> entry : types.entrySet()) {
            int count = (int) Math.round((entry.getValue() / totalWeight) * totalCount);
            for (int i = 0; i < count; i++) {
                try {
                    result.add(entry.getKey().getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    Logger.error("Failed to instantiate entity: " + entry.getKey().getSimpleName());
                    throw new RuntimeException("Cannot create instance of " + entry.getKey(), e);
                }
            }
        }

        Collections.shuffle(result);
        return result;
    }

}
