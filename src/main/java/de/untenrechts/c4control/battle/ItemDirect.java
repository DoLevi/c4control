package de.untenrechts.c4control.battle;

import de.untenrechts.c4control.entrepreneur.EntrepreneurProvider;
import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import de.untenrechts.c4control.network.C4ControlPacketHandler;
import de.untenrechts.c4control.registration.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDirect extends ItemBase {

    public ItemDirect(final String name) {
        super(name, CreativeTabs.COMBAT);
        setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        IEntrepreneur entrepreneur = playerIn.getCapability(EntrepreneurProvider.entrepreneur, null);
        Item usedItem = playerIn.getHeldItem(handIn).getItem();

        if (entrepreneur.isEntrepreneur() && usedItem instanceof ItemDirect) {
            playerIn.setActiveHand(handIn);
            if (!worldIn.isRemote) {
                entrepreneur.startCharge();

                playerIn.sendMessage(new TextComponentString(
                        String.format("Starting to charge from %.2f§mM§r ...",
                                entrepreneur.getActiveChargeValue())));
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        } else {
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        IEntrepreneur entrepreneur = entityLiving.getCapability(EntrepreneurProvider.entrepreneur, null);
        Item usedItem = stack.getItem();

        if (entrepreneur.isEntrepreneur() && usedItem instanceof ItemDirect) {
            if (!worldIn.isRemote) {
                float previousChargeValue = entrepreneur.getActiveChargeValue();

                entrepreneur.stopCharge();
                C4ControlPacketHandler.sendChargeToClient((EntityPlayerMP) entityLiving,
                        entrepreneur.getChargeMultiplier(), entrepreneur.getActiveChargeValue());

                String chargeMessage = String.format("Charged to %.2f§mM§r [%.2f§mM§r].",
                        entrepreneur.getActiveChargeValue(),
                        entrepreneur.getActiveChargeValue() - previousChargeValue);
                String damageMessage = String.format("Expected damage: %.2f",
                        entrepreneur.getExpectedDamage());

                entityLiving.sendMessage(new TextComponentString(chargeMessage));
                entityLiving.sendMessage(new TextComponentString(damageMessage));
            }
        } else {
            super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }
}
