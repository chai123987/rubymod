package net.tutorial.rubymod.item;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tutorial.rubymod.RubyMod;


@Mod.EventBusSubscriber(
        modid = RubyMod.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ModCreativeModeTabs {


    @SubscribeEvent
    public static void addItems(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {


            event.accept(ModItems.RUBY);


        }


        if(event.getTabKey() == CreativeModeTabs.COMBAT) {


            event.accept(ModItems.RUBY_SWORD);


        }


        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {


            event.accept(ModItems.RUBY_PICKAXE);
            event.accept(ModItems.RUBY_AXE);


        }

    }
}
