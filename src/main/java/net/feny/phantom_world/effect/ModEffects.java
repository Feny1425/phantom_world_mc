package net.feny.phantom_world.effect;

import net.feny.phantom_world.PhantomWorld;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect FEAR;
    public static StatusEffect registerStatusEffect(String name){
        return Registry.register(Registries.STATUS_EFFECT,new Identifier(PhantomWorld.MOD_ID,name),
                new FearEffect(StatusEffectCategory.HARMFUL,3131903));
    }
    public static void registerEffects(){
        FEAR = registerStatusEffect("fear");
    }
}
