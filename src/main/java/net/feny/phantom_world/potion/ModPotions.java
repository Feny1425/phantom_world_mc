package net.feny.phantom_world.potion;

import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModPotions {
    public static Potion FEAR_POTION;

    public static Potion registerPotion(String name){
        return Registry.register(Registries.POTION,new Identifier(PhantomWorld.MOD_ID,name),
                new Potion(new StatusEffectInstance(ModEffects.FEAR,200,0)));
    }
    public static void registerPotions(){
        FEAR_POTION = registerPotion("fear_potion");
    }
}
