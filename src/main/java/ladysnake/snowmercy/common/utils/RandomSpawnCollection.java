package ladysnake.snowmercy.common.utils;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomSpawnCollection<E extends EntityType<? extends LivingEntity>> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private double total = 0;

    public RandomSpawnCollection<E> add(double weight, E result) {
        if (weight <= 0) {
            return this;
        }
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next(Random random) {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
}