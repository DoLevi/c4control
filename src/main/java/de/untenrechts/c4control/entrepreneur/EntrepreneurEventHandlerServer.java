package de.untenrechts.c4control.entrepreneur;

import de.untenrechts.c4control.battle.ItemDirect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;


public class EntrepreneurEventHandlerServer {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (isEntrepreneur(event.player)) {
            event.player.sendMessage(new TextComponentString("You are an Entrepreneur."));
        }
    }

    @SubscribeEvent
    public void onPlayerDirectAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        EntityLivingBase target = event.getEntityLiving();

        if (source != null && isC4AttackEvent(source, target)) {
            IEntrepreneur sourceEntrepreneur
                    = source.getCapability(EntrepreneurProvider.entrepreneur, null);
            event.setAmount(sourceEntrepreneur.getExpectedDamage());
        }
    }

    private static boolean isC4AttackEvent(Entity source, Entity target) {
        if (isEntrepreneur(source) && isEntrepreneur(target) && source instanceof EntityPlayer) {
            return ((EntityPlayer) source).getHeldItemMainhand().getItem() instanceof ItemDirect;
        }
        return false;
    }

    private static boolean isEntrepreneur(Entity entrepreneurCandidate) {
        IEntrepreneur entrepreneur
                = entrepreneurCandidate.getCapability(EntrepreneurProvider.entrepreneur, null);
        return entrepreneur != null && entrepreneur.isEntrepreneur();
    }
}
