package com.example.anotheranimerpg.entities.mobs.slimes;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class EarthSlimeEntity extends SlimeEntity{
    public EarthSlimeEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void tick()
    {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<Player> players = this.level.getEntitiesOfClass(Player.class, new AABB(x - 10, y - 10, z -10, x + 10, y + 10, z +10));
        for (Player e: players)
        {
            e.push((x - e.getX()) * 0.005, (y - e.getY()) * 0.005, (z - e.getZ()) * 0.005);
        }
        super.tick();
    }
}
