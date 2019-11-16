package de.untenrechts.c4control.item;

import de.untenrechts.c4control.common.ItemBase;
import de.untenrechts.c4control.common.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMidasNote extends ItemBase {

    public ItemMidasNote() {
        super(ModItems.ITEM_MIDAS_NOTE_NAME);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ResourceLocation registryName = getRegistryName();
        if (registryName == null) {
            throw new IllegalStateException("registryName must not be null!");
        }
        ModelResourceLocation mrl = new ModelResourceLocation(registryName, "inventory");
        ModelLoader.setCustomModelResourceLocation(this, 0, mrl);
    }

}
