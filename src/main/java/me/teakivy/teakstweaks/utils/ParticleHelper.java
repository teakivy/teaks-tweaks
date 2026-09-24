package me.teakivy.teakstweaks.utils;

import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ParticleHelper {

    public static void spawnBoneMealParticles(Block block) {
        BoundingBox box = block.getBoundingBox();

        int count = 15;
        int gridSize = 4;

        double height = box.getHeight();

        List<int[]> cells = new ArrayList<>();

        for (int x = 0; x < gridSize; x++) {
            for (int z = 0; z < gridSize; z++) {
                cells.add(new int[]{x, z});
            }
        }

        Collections.shuffle(cells);

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < count; i++) {
            int[] cell = cells.get(i % cells.size());

            // use 1x1 footprint
            double x = block.getX()
                    + ((cell[0] + random.nextDouble()) / gridSize);

            double z = block.getZ()
                    + ((cell[1] + random.nextDouble()) / gridSize);

            double y;

            if (height < 0.25) {
                // Short blocks:
                // put particles just above the top surface
                y = box.getMaxY() + random.nextDouble(0.02, 0.15);
            } else {
                // Tall blocks:
                // vertical spread follows the actual block height
                y = box.getMinY() + random.nextDouble() * height;

                // Prevent ground clipping
                y = Math.max(y, box.getMinY() + 0.08);
            }

            block.getWorld().spawnParticle(
                    Particle.HAPPY_VILLAGER,
                    x, y, z,
                    1,
                    0, 0, 0,
                    0
            );
        }
    }
}
