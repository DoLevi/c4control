package de.untenrechts.c4control.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.ParametersAreNonnullByDefault;

public class BlockATM extends BlockBase {

    private static final Material material = Material.IRON;
    private static final SoundType soundType = SoundType.METAL;
    private static final int lightLevel = 4;

    public BlockATM(String name) {
        super(material, name);

        setBlockUnbreakable();
        setSoundType(soundType);
        setLightLevel(lightLevel / 16f);
    }

    @Override
    @ParametersAreNonnullByDefault
    public BlockATM setCreativeTab(CreativeTabs tabs) {
        super.setCreativeTab(tabs);
        return this;
    }
}
