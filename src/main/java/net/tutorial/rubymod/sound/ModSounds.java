package net.tutorial.rubymod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tutorial.rubymod.RubyMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RubyMod.MOD_ID);

    public static final RegistryObject<SoundEvent> BUTTERFLY_FLAP = SOUND_EVENTS.register("butterfly_flap",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RubyMod.MOD_ID, "butterfly_flap")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
