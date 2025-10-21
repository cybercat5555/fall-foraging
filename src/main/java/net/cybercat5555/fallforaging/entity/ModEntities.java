package net.cybercat5555.fallforaging.entity;

import net.cybercat5555.fallforaging.FallForaging;
import net.cybercat5555.fallforaging.entity.custom.SquirrelEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SquirrelEntity> SQUIRREL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FallForaging.MOD_ID,  "squirrel"),
       EntityType.Builder.create(SquirrelEntity::new, SpawnGroup.CREATURE)
               .dimensions(0.5f,  0.5f).build());

    public static void registerModEntities(){
        FallForaging.LOGGER.info("Registering mobs for "+ FallForaging.MOD_ID);
    }
}
