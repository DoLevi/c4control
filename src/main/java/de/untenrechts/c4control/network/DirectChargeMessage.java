package de.untenrechts.c4control.network;

import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Optional;

public class DirectChargeMessage implements IMessage {

    private static final String ENTITY_ID_KEY = "entityPlayerID";
    private static final String CHARGE_MULTIPLIER_KEY = "chargeMultiplier";
    private static final String ACTIVE_CHARGE_VALUE = "activeChargeValue";

    private int entityPlayerID;
    private float chargeMultiplier;
    private float activeChargeValue;

    @SuppressWarnings("unused")
    public DirectChargeMessage() {
    }

    public DirectChargeMessage(int entityPlayerID, float chargeMultiplier, float activeChargeValue) {
        this.entityPlayerID = entityPlayerID;
        this.chargeMultiplier = chargeMultiplier;
        this.activeChargeValue = activeChargeValue;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        if (tag != null) {
            entityPlayerID = tag.getInteger(ENTITY_ID_KEY);
            chargeMultiplier = tag.getFloat(CHARGE_MULTIPLIER_KEY);
            activeChargeValue = tag.getFloat(ACTIVE_CHARGE_VALUE);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger(ENTITY_ID_KEY, entityPlayerID);
        tag.setFloat(CHARGE_MULTIPLIER_KEY, chargeMultiplier);
        tag.setFloat(ACTIVE_CHARGE_VALUE, activeChargeValue);

        ByteBufUtils.writeTag(buf, tag);
    }

    public static class DirectChargeMessageHandler implements IMessageHandler<DirectChargeMessage, IMessage> {

        @Override
        public IMessage onMessage(DirectChargeMessage message, MessageContext ctx) {
            Entity targetEntity = Minecraft.getMinecraft()
                    .world
                    .getEntityByID(message.entityPlayerID);

            Optional<IEntrepreneur> entrepreneurOpt = IEntrepreneur.getActiveEntrepreneur(targetEntity);
            entrepreneurOpt.ifPresent(entrepreneur -> {
                entrepreneur.setChargeMultiplier(message.chargeMultiplier);
                entrepreneur.setActiveChargeValue(message.activeChargeValue);
            });

            // no response
            return null;
        }
    }
}
