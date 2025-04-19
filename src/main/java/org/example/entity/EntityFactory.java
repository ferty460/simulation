package org.example.entity;

import org.example.entity.creature.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class EntityFactory {

    private final Map<Class<? extends Entity>, Double> staticEntityTypes;
    private final Map<Class<? extends Creature>, Double> creatureTypes;

    public EntityFactory(
            Map<Class<? extends Entity>, Double> staticEntityTypes,
            Map<Class<? extends Creature>, Double> creatureTypes
    ) {
        this.staticEntityTypes = staticEntityTypes;
        this.creatureTypes = creatureTypes;
        if (staticEntityTypes.isEmpty() && creatureTypes.isEmpty()) {
            throw new IllegalArgumentException("No entity types provided");
        }
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
                result.add(createEntity(entry.getKey()));
            }
        }

        Collections.shuffle(result);
        return result;
    }

    private <T extends Entity> T createEntity(Class<? extends T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create instance of " + type, e);
        }
    }

}
