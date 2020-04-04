package de.untenrechts.c4control.overworld;

import de.untenrechts.c4control.entrepreneur.EntrepreneurProvider;
import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import de.untenrechts.c4control.network.C4ControlPacketHandler;
import de.untenrechts.c4control.network.DirectChargeMessage;
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
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
                        String.format("Starting to charge up from %s§mM§r",
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
                entrepreneur.stopCharge();
                IMessage msg = new DirectChargeMessage(entrepreneur.getChargeMultiplier(),
                        entrepreneur.getActiveChargeValue(), entityLiving.getUniqueID());
                C4ControlPacketHandler.INSTANCE.sendTo(msg, (EntityPlayerMP) entityLiving);

                entityLiving.sendMessage(new TextComponentString(
                        String.format("Charged up to %s§mM§r",
                                entrepreneur.getActiveChargeValue())));
                entityLiving.sendMessage(new TextComponentString(
                        String.format("Expected damage: %s",
                                entrepreneur.getExpectedDamage())));
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
