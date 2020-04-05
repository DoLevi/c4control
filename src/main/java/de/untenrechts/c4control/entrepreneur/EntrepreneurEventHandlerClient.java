package de.untenrechts.c4control.entrepreneur;

import de.untenrechts.c4control.network.C4ControlPacketHandler;
import de.untenrechts.c4control.network.DirectShiftMessage;
import de.untenrechts.c4control.overworld.ItemDirect;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;


public class EntrepreneurEventHandlerClient {

    private static KeyBinding[] keyBindings = new KeyBinding[] {
            new KeyBinding("c4control.key.shift", Keyboard.KEY_LSHIFT, "c4control.category")
    };

    private boolean wasShiftPressed = false;

    @SubscribeEvent
    public void onPlayerDirectShift(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Arrays.stream(keyBindings)
                    .forEach(binding -> handlePotentialShift(event.player, binding));
        }
    }

    private void handlePotentialShift(EntityPlayer player, KeyBinding binding) {
        if (player.getHeldItemMainhand().getItem() instanceof ItemDirect
                && binding.getKeyCode() == Keyboard.KEY_LSHIFT) {
            boolean shiftState = binding.isKeyDown();
            if (wasShiftPressed != shiftState) {
                IMessage msg = new DirectShiftMessage(player.getUniqueID(), shiftState);
                C4ControlPacketHandler.sendToServer(msg);
                wasShiftPressed = shiftState;
            }
        }
    }
}
