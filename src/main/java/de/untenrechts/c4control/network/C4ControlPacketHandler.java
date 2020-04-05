package de.untenrechts.c4control.network;

import de.untenrechts.c4control.C4Control;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class C4ControlPacketHandler {

    private static final SimpleNetworkWrapper INSTANCE
            = NetworkRegistry.INSTANCE.newSimpleChannel(String.format("%s.main", C4Control.MOD_ID));

    public static void init() {
        INSTANCE.registerMessage(DirectChargeMessage.DirectChargeMessageHandler.class, DirectChargeMessage.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(DirectShiftMessage.DirectShiftMessageHandler.class, DirectShiftMessage.class, 1, Side.SERVER);
    }

    public static void sendChargeToClient(EntityPlayerMP player,
                                          float chargeMultiplier,
                                          float activeChargeValue) {
        C4Control.log.debug("Sending message to client for player {}.", player.getUniqueID());
        DirectChargeMessage message
                = new DirectChargeMessage(player.getEntityId(),chargeMultiplier, activeChargeValue);
        C4ControlPacketHandler.INSTANCE.sendTo(message, player);
    }

    public static void sendToServer(IMessage message) {
        C4Control.log.debug("Sending message to Server.");
        C4ControlPacketHandler.INSTANCE.sendToServer(message);
    }

}
