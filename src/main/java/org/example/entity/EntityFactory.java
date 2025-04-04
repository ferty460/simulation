package org.example.entity;

import org.example.entity.creature.Creature;

import java.util.List;
import java.util.Random;

public class EntityFactory {

    private final Random rand = new Random();
    private final List<Class<? extends Entity>> staticEntityTypes;
    private final List<Class<? extends Creature>> creatureTypes;

    public EntityFactory(
            List<Class<? extends Entity>> staticEntityTypes,
            List<Class<? extends Creature>> creatureTypes
    ) {
        this.staticEntityTypes = staticEntityTypes;
        this.creatureTypes = creatureTypes;
    }

    public Entity createRandomStaticEntity() {
        if (staticEntityTypes.isEmpty()) {
            throw new IllegalStateException("No static entities registered");
        }
        return createInstance(staticEntityTypes);
    }

    public Creature createRandomCreature() {
        if (creatureTypes.isEmpty()) {
            throw new IllegalStateException("No creatures registered");
        }
        return createInstance(creatureTypes);
    }

    private <T> T createInstance(List<Class<? extends T>> types) {
        try {
            return types.get(rand.nextInt(types.size()))
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance", e);
        }
    }

}
