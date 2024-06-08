package tterrag.customthings;

import com.enderio.core.IEnderMod;
import com.enderio.core.common.compat.CompatRegistry;
import com.enderio.core.common.util.RegisterTime;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import tterrag.customthings.common.command.CommandCustomThings;
import tterrag.customthings.common.config.ConfigHandler;
import tterrag.customthings.common.config.json.items.ItemType;
import tterrag.customthings.common.handlers.FuelHandler;

@Mod(modid = "customthings", name = "Custom Things", version = "MC1.7.10-0.0.3-55", dependencies = "required-after:endercore@[0.4.6,)")
public class CustomThings implements IEnderMod {
  public static final String MODID = "customthings";
  
  public static final String NAME = "Custom Things";
  
  public static final String VERSION = "MC1.7.10-0.0.3-55";
  
  public static final String DEPENDENCIES = "required-after:endercore@[0.4.6,)";
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    ConfigHandler.preInit(event);
    CompatRegistry.INSTANCE.registerCompat(RegisterTime.POSTINIT, "tterrag.customthings.common.nei.NEIHider", new String[] { "NotEnoughItems" });
    if (event.getSide().isClient())
      ClientCommandHandler.instance.func_71560_a((ICommand)new CommandCustomThings()); 
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
    ConfigHandler.init();
    GameRegistry.registerFuelHandler((IFuelHandler)new FuelHandler());
  }
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    ConfigHandler.postInit();
  }
  
  @EventHandler
  public void onMissingMapping(FMLMissingMappingsEvent event) {
    for (FMLMissingMappingsEvent.MissingMapping m : event.get()) {
      if (m.type == GameRegistry.Type.ITEM && m.name.contains("customthings.item"))
        m.remap(ItemType.getItem()); 
    } 
  }
  
  public String modid() {
    return "customthings";
  }
  
  public String name() {
    return "Custom Things";
  }
  
  public String version() {
    return "MC1.7.10-0.0.3-55";
  }
}

