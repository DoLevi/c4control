package de.untenrechts.c4control.bank;

import de.untenrechts.c4control.C4Control;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class AtmGui extends GuiContainer {

    private static final int WIDTH = 180;
    private static final int HEIGHT = 152;
    private static final ResourceLocation BACKGROUND = new ResourceLocation(C4Control.MOD_ID, "textures/gui/atm_gui.png");

    public AtmGui(AtmContainer atmContainer) {
        super(atmContainer);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
