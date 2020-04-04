package de.untenrechts.c4control.mod;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.entrepreneur.EntrepreneurProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class CapabilityRegistrationHandler {

    public static final ResourceLocation ENTREPRENEUR_RESOURCE_LOCATION
            = new ResourceLocation(C4Control.MOD_ID, "entrepreneur");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(ENTREPRENEUR_RESOURCE_LOCATION, new EntrepreneurProvider());
        }
        // TODO: 4/3/20 remove this
        if (event.getObject() instanceof EntitySlime) {
            event.addCapability(ENTREPRENEUR_RESOURCE_LOCATION, new EntrepreneurProvider());
        }
    }
}
