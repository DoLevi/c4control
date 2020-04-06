package de.untenrechts.c4control.entrepreneur;

import de.untenrechts.c4control.battle.ItemDirect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public abstract class EntrepreneurEventHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (isEntrepreneur(event.player)) {
            event.player.sendMessage(new TextComponentString("You are an Entrepreneur."));
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onPlayerDirectAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        Entity target = event.getEntityLiving();

        if (source != null && isC4AttackEvent(source, target)) {
            IEntrepreneur.getActiveEntrepreneur(source)
                    .ifPresent(entrepreneur -> event.setAmount(entrepreneur.getExpectedDamage()));
        }
    }

    private static boolean isC4AttackEvent(Entity source, Entity target) {
        if (isEntrepreneur(source) && isEntrepreneur(target) && source instanceof EntityPlayer) {
            return ((EntityPlayer) source).getHeldItemMainhand().getItem() instanceof ItemDirect;
        }
        return false;
    }

    private static boolean isEntrepreneur(Entity entity) {
        return IEntrepreneur.getActiveEntrepreneur(entity).isPresent();
    }
}
