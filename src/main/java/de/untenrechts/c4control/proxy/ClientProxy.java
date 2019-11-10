package de.untenrechts.c4control.proxy;

import de.untenrechts.c4control.C4Control;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {


    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        String mrlString = String.format("%s:%s", C4Control.MODID, id);
        ModelResourceLocation location = new ModelResourceLocation(mrlString, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
    }
}
