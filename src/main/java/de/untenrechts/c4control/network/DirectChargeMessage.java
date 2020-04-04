package de.untenrechts.c4control.network;

import de.untenrechts.c4control.entrepreneur.EntrepreneurProvider;
import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class DirectChargeMessage implements IMessage {

    private static final String ENTITY_ID_KEY = "entityPlayerUUID";
    private static final String CHARGE_MULTIPLIER_KEY = "chargeMultiplier";
    private static final String ACTIVE_CHARGE_VALUE = "activeChargeValue";

    private UUID entityPlayerUUID;
    private float chargeMultiplier;
    private float activeChargeValue;

    public DirectChargeMessage() {
    }

    public DirectChargeMessage(float chargeMultiplier, float activeChargeValue, UUID entityPlayerUUID) {
        this.entityPlayerUUID = entityPlayerUUID;
        this.chargeMultiplier = chargeMultiplier;
        this.activeChargeValue = activeChargeValue;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        entityPlayerUUID = tag.getUniqueId(ENTITY_ID_KEY);
        chargeMultiplier = tag.getFloat(CHARGE_MULTIPLIER_KEY);
        activeChargeValue = tag.getFloat(ACTIVE_CHARGE_VALUE);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setUniqueId(ENTITY_ID_KEY, entityPlayerUUID);
        tag.setFloat(CHARGE_MULTIPLIER_KEY, chargeMultiplier);
        tag.setFloat(ACTIVE_CHARGE_VALUE, activeChargeValue);

        ByteBufUtils.writeTag(buf, tag);
    }

    public static class DirectChargeMessageHandler implements IMessageHandler<DirectChargeMessage, IMessage> {

        @Override
        public IMessage onMessage(DirectChargeMessage message, MessageContext ctx) {
            EntityPlayer targetEntity = Minecraft.getMinecraft()
                    .world
                    .getPlayerEntityByUUID(message.entityPlayerUUID);

            IEntrepreneur entrepreneur
                    = targetEntity.getCapability(EntrepreneurProvider.entrepreneur, null);
            entrepreneur.setChargeMultiplier(message.chargeMultiplier);
            entrepreneur.setActiveChargeValue(message.activeChargeValue);

            // no response
            return null;
        }
    }
}
