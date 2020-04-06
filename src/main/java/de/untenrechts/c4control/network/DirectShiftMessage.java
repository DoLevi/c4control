package de.untenrechts.c4control.network;

import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class DirectShiftMessage implements IMessage {

    private static final String ENTITY_ID_KEY = "entityPlayerUUID";
    private static final String NEW_SHIFT_STATE_KEY = "newShiftState";

    private UUID entityPlayerUUID;
    private boolean newShiftState;

    @SuppressWarnings("unused")
    public DirectShiftMessage() {
    }

    public DirectShiftMessage(UUID entityPlayerUUID, boolean newShiftState) {
        this.entityPlayerUUID = entityPlayerUUID;
        this.newShiftState = newShiftState;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        if (tag != null) {
            entityPlayerUUID = tag.getUniqueId(ENTITY_ID_KEY);
            newShiftState = tag.getBoolean(NEW_SHIFT_STATE_KEY);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setUniqueId(ENTITY_ID_KEY, entityPlayerUUID);
        tag.setBoolean(NEW_SHIFT_STATE_KEY, newShiftState);

        ByteBufUtils.writeTag(buf, tag);
    }

    public static class DirectShiftMessageHandler implements IMessageHandler<DirectShiftMessage, IMessage> {

        @Override
        public IMessage onMessage(DirectShiftMessage message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;

            IEntrepreneur.getActiveEntrepreneur(player)
                    .ifPresent(entrepreneur -> entrepreneur.setDischargeEnabled(message.newShiftState));

            // no response
            return null;
        }
    }
}
